Describe "Download-ChromeDriver Tests" {

    It "Should download and save ChromeDriver to the latest version" {
        
        $latestVersion = "114.0.5735.90"
        $zipFile = "Simulated zip file"

        $mockZip = @{
            Content = $zipFile
        }

        # Mock web request response for downloading zip file
        Mock Invoke-WebRequest {
            # Simulated downloaded zip file
            return $mockZip
        }

        #Leaving these cases empty as they do not play a role in this test case###

        # Mock Expand-Archive
        Mock Expand-Archive {
            # Can't test the expand because there is no actual zip file to expand
        }
        # Mock file operations
        Mock Copy-Item {
            # Not testing the copy as there is nothing new to copy
        }
        Mock Remove-Item {
            # Do not want to delete any files in a test enviornment
        }
        Mock Out-File {
            # Not actually saving a version to a text file, no need to run this 
        }
        ##########################################################################

        # Call the function
        $result = Download-ChromeDriver -latestVersion $latestVersion -currentUser "TestUser"

        $result | Should -Be $true

    }

}
