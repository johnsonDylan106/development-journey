$getFunctions = Join-Path -Path $PSScriptRoot -ChildPath "MyFunction.ps1"
. $getFunctions

$host.ui.RawUI.WindowTitle = "Updating ChromeDriver"
Write-Host "Updating ChromeDriver`n`n"

$currentUser = Get-ActiveUser
$fileSaveLocation = "C:\Users\" + $currentUser +"\Documents"
$fileLocation = "$fileSaveLocation\chromeDriverVersion.txt"
cd $fileSaveLocation

Update-ChromeDriver -fileLocation $fileLocation -fileSaveLocation $fileSaveLocation