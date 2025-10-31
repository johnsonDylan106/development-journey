<#

To be finished at a later date

Describe "Test-Module Test" {

    It "Should import the requested module" {
    
        $moduleName = "testModule"

        Mock Import-Module {
            # Nothing needs to happen here
        }

        Mock Get-Module {
            return [PSCustomObject]@{
                Name = $moduleName
            }
        }

        Mock Write-Host {
            return "Imported $moduleName"
        }
        
        $result = Test-Module -moduleName $moduleName
        $result | Should -be "Imported $moduleName"
    
    }

    It "Should install and import the missing module" {

        $moduleName = "testModule"

        Mock Get-Module {
            return $null
        }

        Mock Import-Module {
            # Nothing, same as above
        }

        Mock Install-Module {
            return $true
        }

        Mock Write-Host {
            return "$moduleName has been installed"
        }

        $result = Test-Module -moduleName $moduleName
        $result | Should -Be "$moduleName has been installed"

    }

}
#>