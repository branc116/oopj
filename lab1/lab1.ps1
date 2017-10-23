Function Start-Command ($commandTitle, $commandPath, $commandArguments, $workingDirectory)
{
    $curL = Get-Location;
    $pinfo = New-Object System.Diagnostics.ProcessStartInfo;
    $pinfo.FileName = $commandPath;
    $pinfo.RedirectStandardError = $true;
    $pinfo.RedirectStandardOutput = $true;
    $pinfo.UseShellExecute = $false;
    $pinfo.Arguments = $commandArguments;
    $pinfo.WorkingDirectory = $curL.Path;
    $p = New-Object System.Diagnostics.Process;
    $p.StartInfo = $pinfo;
    $startTime = [System.DateTime]::Now;
    $p.Start() | Out-Null;
    $p.WaitForExit();
    [pscustomobject]@{
        commandTitle = $commandTitle;
        stdout = $p.StandardOutput.ReadToEnd();
        stderr = $p.StandardError.ReadToEnd();
        ExitCode = $p.ExitCode;
        ExTime = [System.DateTime]::Now - $startTime;
    }
}
$a = (Get-ChildItem -Recurse -File | Where-Object Name -Like *.java);
$javaFiles = [system.String]::Join(" ", $a.FullName);
$arg = "-d .\bin " + $javaFiles;
$jarArgs = "cf lab.jar .\bin";
Write-Output ("Compiling java files:", $javaFiles);
Start-Process javac -Wait -ArgumentList $arg -WindowStyle Hidden -WorkingDirectory "." -RedirectStandardOutput "javacompile.log" -RedirectStandardError "javacompile_error.log";
Start-Process jar -Wait -ArgumentList $jarArgs -WindowStyle Hidden;
Write-Output ("Compile errors: ", (Get-Content javacompile_error.log));
$javaMains = $a | Where-Object { 
    (Get-Content $_.FullName) -like "*public static void main*";
    # FullName -Like *prob*;
};
$javaInputs = Get-ChildItem $javaMains.Directory -File | Where-Object Name -like *input*;
([System.IO.FileInfo[]]$javaMains).FullName.Replace('\', '.').Split([system.string[]](".src."), [System.StringSplitOptions]::RemoveEmptyEntries).Replace(".java", "") | 
    Where-Object {$_ -notlike "*:*"} | 
    ForEach-Object -Process {
        $cur = $_;
        $inputs = $javaInputs | Where-Object {
            $probName = $_.Directory.FullName.Split('\');
            ([System.String]$cur).Contains($probName[$probName.Length - 1]);
        };
        $inputs | ForEach-Object -Process {
            $content =  get-content $_.FullName;
            $processArgs = "-cp .\bin " + $cur + " " + $content;
            Write-Output ("Starting new java test with starting args:", $processArgs.TrimEnd(' '));
            $out = Start-Command -commandTitle $_.Name -commandPath java -workingDirectory .\ -commandArguments $processArgs.TrimEnd(' ');
            Write-Output ("Exit code:", $out.ExitCode, "StdOut:", $out.stdout, "StdErr:", $out.stderr , "Execution time:", ([System.TimeSpan]$out.ExTime).TotalMilliseconds, [System.String]::new('-', [System.Console]::WindowWidth));
        }
    }