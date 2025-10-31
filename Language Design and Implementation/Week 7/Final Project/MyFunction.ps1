Function Get-ActiveUser {
    
    # Returns the active user of the computer
    $env:UserName 

}

Function Get-LatestChromeVersion {
    
    # Obtains the latest version of the chromedriver from the offical website and stores in the variable latestVersion
    Invoke-WebRequest -Uri "https://chromedriver.storage.googleapis.com/LATEST_RELEASE" | Select-Object -ExpandProperty Content

}

Function Get-CurrentChromeVersion {
    
    [CmdletBinding()]
    param (
        [String]$fileLocation
    )
    
    # Checks for current saved version, sets to 0 if no version available#################
    if (Test-Path $fileLocation) {
        return $chromeVersion = Get-Content -Path $fileLocation
    } else {
        return $chromeVersion = 0
    }
    ######################################################################################
}

Function Update-ChromeDriver {
    
    [CmdletBinding()]
    param (
        $fileLocation,
        $fileSaveLocation
    )
    
    $currentVersion = Get-CurrentChromeVersion -fileLocation $fileLocation
    
    # Updates to current version if not already there###############################
    if ((Get-LatestChromeVersion) -ne $currentVersion) {        
        Download-ChromeDriver -latestVersion (Get-LatestChromeVersion) -currentUser (Get-ActiveUser) -fileSaveLocation $fileSaveLocation
        pause
    }
    else {
        Write-Host "Chromedriver is already up to date"
        Pause
    }
    ################################################################################

}

Function Download-ChromeDriver {
    
    [CmdletBinding()]
    param (
        $latestVersion,
        $currentUser,
        $fileSaveLocation
    )

    Write-Host "Updating Chromedriver to version $latestVersion"

    #Variable for the file path because Invoke-WebRequest does not allow for the + sign to include the variable of the version.
    $URI = "https://chromedriver.storage.googleapis.com/" + $latestVersion + "/chromedriver_win32.zip"

    # Download the latest version of Chromedriver
    Invoke-WebRequest -Uri $URI -OutFile .\chromedriver.zip
    Write-Host "chromedriver.zip successfully downloaded"

    # Extract the Chromedriver executable
    Expand-Archive .\chromedriver.zip -DestinationPath "$fileSaveLocation\WindowsPowerShell" -Force
    
    # Clean up the downloaded zip file
    Remove-Item .\chromedriver.zip
    Write-Host "chromedriver.zip has been deleted"

    #Write the version of the chromedriver to a file for next run
    Copy-Item "$fileSaveLocation\WindowsPowerShell\chromedriver.exe" -Destination "$fileSaveLocation\WindowsPowerShell\Modules\Selenium\3.0.1\assemblies" -Force
    Write-Host "chromedriver.exe has been moved to proper directory"
    
    #Saves the current version number of the downloaded webdriver so this section only runs if this file doesn't exist or doesn't match
    $latestVersion | Out-File "$fileSaveLocation\chromeDriverVersion.txt"
    Write-Host "current version saved to file"
    Remove-Item "$fileSaveLocation\WindowsPowerShell\chromedriver.exe"
    Write-Host "Deleted second copy of chromedriver.exe"

}


<#

I really wanted to add this as a fucntion as well with it's own set of tests but I just ran out of time..
It also really isn't relevent her

function Test-Module {

    [CmdletBinding()]
    param (
        [String]$moduleName
    )

    if (Get-Module -ListAvailable -Name $moduleName) {
        Import-Module $moduleName
        Write-Host "Imported $moduleName"
    } else {
        Write-Host "You are about to install the $moduleName module to your documents folder."
        Write-Host "Press enter if you wish tso continue with this installation"
        Write-Host "You may be asked to accept permissions. Please select the 'Yes To All' Option`n`n"
        Write-Host "If you do not know what this means, or you are running into other errors, please close this window and contact your tech team"
        pause
        Install-Module $moduleName -Scope CurrentUser
        Write-Host "$moduleName has been installed"
    }

}
#>