Describe "Get-LatestChromeVersion Tests" {
    
    It "Should return the latest ChromeDriver version" {
    
        # Mock the web request response
        Mock Invoke-WebRequest {
            return [PSCustomObject]@{
                # Simulated latest version
                Content = "114.0.5735.90"  
            }
        }

        $latestVersion = Get-LatestChromeVersion

        $latestVersion | Should -BeExactly "114.0.5735.90"
    
    }

}
