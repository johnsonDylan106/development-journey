Describe "Get-CurrentInstallVersion Tests" {

    It "Should return the current installed version" {

        Mock Get-Content {
            # Simulated current version
            return "114.0.5735.90"
        }

        $currentVersion = Get-CurrentInstallVersion

        $currentVersion | Should -BeExactly "114.0.5735.90"

    }

}
