Get-Content The_village_traffic_light_in.txt | js solution.js > The_village_traffic_light_out.txt 2>> error.txt

If((Get-FileHash The_village_traffic_light_out.txt).hash  -ne (Get-FileHash The_village_traffic_light_expected.txt).hash) {
    "files are different"
    Get-Content The_village_traffic_light_out.txt
    Get-Content The_village_traffic_light_expected.txt
}
Else {"Files are the same"}