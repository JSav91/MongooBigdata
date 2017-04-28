package com.unitec.ade.business;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import com.unitec.ade.entity.CaseRecord;
import com.unitec.ade.entity.Perpetrator;
import com.unitec.ade.entity.Relationship;
import com.unitec.ade.entity.Victim;

/**
 * Created by roland on 20/04/17.
 */
public class RandomDataGenerator {

    private String[] mCaseStates = {"unsolved", "solved"};
    private String[] mCrimes = {
            "murder or manslaughter",
            "manslaughter by negligence",
            "assault",
            "assault with deadly weapon",
            "burglary",
            "common theft",
            "theft with a deadly weapon"};
    private String[] mEthnicity = {"maori",
            "pacific",
            "asian",
            "european",
            "african",
            "arabic",
            "indian",
            "hispanic"};
    private String[] mGender = {"male",
            "female"};
    private String[] weapon={".45 calliber pistol","Hangun","Baseball Bat","Shovel",".38 revolver","Crowbar","Shotgun","Knife","Stick"};
    private String[][] mLocationAndAgency = {{"Prattville", "Municipal Police", "Autauga", "Alabama"}, {"Orange Beach", "Municipal Police", "Baldwin", "Alabama"}, {"Baker Hill", "Municipal Police", "Barbour", "Alabama"}, {"Bibb", "Sheriff", "Bibb", "Alabama"}, {"Snead", "Municipal Police", "Blount", "Alabama"}, {"Union Springs", "Municipal Police", "Bullock", "Alabama"}, {"Mckenzie", "Municipal Police", "Butler", "Alabama"}, {"Jacksonville State University", "Special Police", "Calhoun", "Alabama"}, {"Valley", "Municipal Police", "Chambers", "Alabama"}, {"Cedar Bluff", "Municipal Police", "Cherokee", "Alabama"}, {"Thorsby", "Municipal Police", "Chilton", "Alabama"}, {"Pennington", "Municipal Police", "Choctaw", "Alabama"}, {"Grove Hill", "Municipal Police", "Clarke", "Alabama"}, {"Lineville", "Municipal Police", "Clay", "Alabama"}, {"Heflin", "Municipal Police", "Cleburne", "Alabama"}, {"Enterprise", "Municipal Police", "Coffee", "Alabama"}, {"Cherokee", "Municipal Police", "Colbert", "Alabama"}, {"Evergreen", "Municipal Police", "Conecuh", "Alabama"}, {"Rockford", "Municipal Police", "Coosa", "Alabama"}, {"Red Level", "Municipal Police", "Covington", "Alabama"}, {"Brantley", "Municipal Police", "Crenshaw", "Alabama"}, {"Hanceville", "Municipal Police", "Cullman", "Alabama"}, {"Midland City", "Municipal Police", "Dale", "Alabama"}, {"Selma", "Municipal Police", "Dallas", "Alabama"}, {"Rainsville", "Municipal Police", "De Kalb", "Alabama"}, {"Millbrook", "Municipal Police", "Elmore", "Alabama"}, {"Flomaton", "Municipal Police", "Escambia", "Alabama"}, {"Southside", "Municipal Police", "Etowah", "Alabama"}, {"Berry", "Municipal Police", "Fayette", "Alabama"}, {"Red Bay", "Municipal Police", "Franklin", "Alabama"}, {"Slocomb", "Municipal Police", "Geneva", "Alabama"}, {"Eutaw", "Municipal Police", "Greene", "Alabama"}, {"Greensboro", "Municipal Police", "Hale", "Alabama"}, {"Headland", "Municipal Police", "Henry", "Alabama"}, {"Ashford", "Municipal Police", "Houston", "Alabama"}, {"Hollywood", "Municipal Police", "Jackson", "Alabama"}, {"Warrior", "Municipal Police", "Jefferson", "Alabama"}, {"Sulligent", "Municipal Police", "Lamar", "Alabama"}, {"Killen", "Municipal Police", "Lauderdale", "Alabama"}, {"Courtland", "Municipal Police", "Lawrence", "Alabama"}, {"Auburn University: Main Campus", "Special Police", "Lee", "Alabama"}, {"Athens", "Municipal Police", "Limestone", "Alabama"}, {"Mosses", "Municipal Police", "Lowndes", "Alabama"}, {"Shorter", "Municipal Police", "Macon", "Alabama"}, {"Owens Crossroads", "Municipal Police", "Madison", "Alabama"}, {"Demopolis", "Municipal Police", "Marengo", "Alabama"}, {"Bear Creek", "Municipal Police", "Marion", "Alabama"}, {"Boaz", "Municipal Police", "Marshall", "Alabama"}, {"Creola", "Municipal Police", "Mobile", "Alabama"}, {"Monroeville", "Municipal Police", "Monroe", "Alabama"}, {"Alabama Department of Mental Health", "Special Police", "Montgomery", "Alabama"}, {"Priceville", "Municipal Police", "Morgan", "Alabama"}, {"Uniontown", "Municipal Police", "Perry", "Alabama"}, {"Pickensville", "Municipal Police", "Pickens", "Alabama"}, {"Troy State University", "Special Police", "Pike", "Alabama"}, {"Roanoke", "Municipal Police", "Randolph", "Alabama"}, {"Hurtsboro", "Municipal Police", "Russell", "Alabama"}, {"Helena", "Municipal Police", "Shelby", "Alabama"}, {"Riverside", "Municipal Police", "St. Clair", "Alabama"}, {"Cuba", "Municipal Police", "Sumter", "Alabama"}, {"Lincoln", "Municipal Police", "Talladega", "Alabama"}, {"Camp Hill", "Municipal Police", "Tallapoosa", "Alabama"}, {"Northport", "Municipal Police", "Tuscaloosa", "Alabama"}, {"Parrish", "Municipal Police", "Walker", "Alabama"}, {"Chatom", "Municipal Police", "Washington", "Alabama"}, {"Pine Hill", "Municipal Police", "Wilcox", "Alabama"}, {"Haleyville", "Municipal Police", "Winston", "Alabama"}, {"St. Paul", "Municipal Police", "Aleutians West", "Alaska"}, {"Alaska State Police", "State Police", "Anchorage", "Alaska"}, {"Bethel", "Municipal Police", "Bethel", "Alaska"}, {"Bristol Bay Borough", "County Police", "Bristol Bay", "Alaska"}, {"Dillingham", "Municipal Police", "Dillingham", "Alaska"}, {"Fairbanks", "Municipal Police", "Fairbanks North Star", "Alaska"}, {"Haines", "Municipal Police", "Haines", "Alaska"}, {"Alaska State Police", "State Police", "Juneau", "Alaska"}, {"Kenai", "Municipal Police", "Kenai Peninsula", "Alaska"}, {"Ketchikan", "Municipal Police", "Ketchikan Gateway", "Alaska"}, {"Kodiak", "Municipal Police", "Kodiak Island", "Alaska"}, {"Wasilla", "Municipal Police", "Matanuska-Susitna", "Alaska"}, {"Nome", "Municipal Police", "Nome", "Alaska"}, {"North Slope Borough", "County Police", "North Slope", "Alaska"}, {"Kotzebue", "Municipal Police", "Northwest Arctic", "Alaska"}, {"Craig", "Municipal Police", "Prince of Wales-Outer Ketchikan", "Alaska"}, {"Sitka", "Municipal Police", "Sitka", "Alaska"}, {"Skagway", "Municipal Police", "Skagway-Hoonah-Angoon", "Alaska"}, {"Wrangell", "Municipal Police", "Wrangell-Petersburg", "Alaska"}, {"Nenana", "Municipal Police", "Yukon-Koyukuk", "Alaska"}, {"Springerville", "Municipal Police", "Apache", "Arizona"}, {"Willcox", "Municipal Police", "Cochise", "Arizona"}, {"Page", "Municipal Police", "Coconino", "Arizona"}, {"Winkelman", "Municipal Police", "Gila", "Arizona"}, {"Thatcher", "Municipal Police", "Graham", "Arizona"}, {"Greenlee County", "Sheriff", "Greenlee", "Arizona"}, {"Quartzsite", "Municipal Police", "La Paz", "Arizona"}, {"Arizona State University: Main Camp", "Special Police", "Maricopa", "Arizona"}, {"Bullhead City", "Municipal Police", "Mohave", "Arizona"}, {"Pinetop-Lakeside", "Municipal Police", "Navajo", "Arizona"}, {"University of Arizona", "Special Police", "Pima", "Arizona"}, {"Maricopa", "Municipal Police", "Pinal", "Arizona"}, {"Nogales", "Municipal Police", "Santa Cruz", "Arizona"}, {"Sedona", "Municipal Police", "Yavapai", "Arizona"}, {"San Luis", "Municipal Police", "Yuma", "Arizona"}, {"Stuttgart", "Municipal Police", "Arkansas", "Arkansas"}, {"Hamburg", "Municipal Police", "Ashley", "Arkansas"}, {"Gassville", "Municipal Police", "Baxter", "Arkansas"}, {"Bella Vista", "Municipal Police", "Benton", "Arkansas"}, {"Harrison", "Municipal Police", "Boone", "Arkansas"}, {"Warren", "Municipal Police", "Bradley", "Arkansas"}, {"Hampton", "Municipal Police", "Calhoun", "Arkansas"}, {"Green Forest", "Municipal Police", "Carroll", "Arkansas"}, {"Lake Village", "Municipal Police", "Chicot", "Arkansas"}, {"Gurdon", "Municipal Police", "Clark", "Arkansas"}, {"Piggott", "Municipal Police", "Clay", "Arkansas"}, {"Heber Springs", "Municipal Police", "Cleburne", "Arkansas"}, {"Rison", "Municipal Police", "Cleveland", "Arkansas"}, {"Magnolia", "Municipal Police", "Columbia", "Arkansas"}, {"Morrilton", "Municipal Police", "Conway", "Arkansas"}, {"Arkansas State University", "Special Police", "Craighead", "Arkansas"}, {"Alma", "Municipal Police", "Crawford", "Arkansas"}, {"Marion", "Municipal Police", "Crittenden", "Arkansas"}, {"Wynne", "Municipal Police", "Cross", "Arkansas"}, {"Fordyce", "Municipal Police", "Dallas", "Arkansas"}, {"Dumas", "Municipal Police", "Desha", "Arkansas"}, {"University of Arkansas: Monticello", "Special Police", "Drew", "Arkansas"}, {"University of Central Arkansas", "Special Police", "Faulkner", "Arkansas"}, {"Ozark", "Municipal Police", "Franklin", "Arkansas"}, {"Salem", "Municipal Police", "Fulton", "Arkansas"}, {"Hot Springs", "Municipal Police", "Garland", "Arkansas"}, {"Sheridan", "Municipal Police", "Grant", "Arkansas"}, {"Marmaduke", "Municipal Police", "Greene", "Arkansas"}, {"Hope", "Municipal Police", "Hempstead", "Arkansas"}, {"Malvern", "Municipal Police", "Hot Spring", "Arkansas"}, {"Nashville", "Municipal Police", "Howard", "Arkansas"}, {"Independence County", "Sheriff", "Independence", "Arkansas"}, {"Izard County", "Sheriff", "Izard", "Arkansas"}, {"Tuckerman", "Municipal Police", "Jackson", "Arkansas"}, {"University of Arkansas: Pine Bluff", "Special Police", "Jefferson", "Arkansas"}, {"Clarksville", "Municipal Police", "Johnson", "Arkansas"}, {"Lewisville", "Municipal Police", "Lafayette", "Arkansas"}, {"Walnut Ridge", "Municipal Police", "Lawrence", "Arkansas"}, {"Marianna", "Municipal Police", "Lee", "Arkansas"}, {"Star City", "Municipal Police", "Lincoln", "Arkansas"}, {"Ashdown", "Municipal Police", "Little River", "Arkansas"}, {"Paris", "Municipal Police", "Logan", "Arkansas"}, {"Ward", "Municipal Police", "Lonoke", "Arkansas"}, {"Huntsville", "Municipal Police", "Madison", "Arkansas"}, {"Flippin", "Municipal Police", "Marion", "Arkansas"}, {"Texarkana", "Municipal Police", "Miller", "Arkansas"}, {"Osceola", "Municipal Police", "Mississippi", "Arkansas"}, {"Clarendon", "Municipal Police", "Monroe", "Arkansas"}, {"Montgomery County", "Sheriff", "Montgomery", "Arkansas"}, {"Prescott", "Municipal Police", "Nevada", "Arkansas"}, {"Newton", "Sheriff", "Newton", "Arkansas"}, {"Bearden", "Municipal Police", "Ouachita", "Arkansas"}, {"Perry", "Sheriff", "Perry", "Arkansas"}, {"Elaine", "Municipal Police", "Phillips", "Arkansas"}, {"Glenwood", "Municipal Police", "Pike", "Arkansas"}, {"Harrisburg", "Municipal Police", "Poinsett", "Arkansas"}, {"Mena", "Municipal Police", "Polk", "Arkansas"}, {"Pottsville", "Municipal Police", "Pope", "Arkansas"}, {"De Valls Bluff", "Municipal Police", "Prairie", "Arkansas"}, {"Maumelle", "Municipal Police", "Pulaski", "Arkansas"}, {"Pocahontas", "Municipal Police", "Randolph", "Arkansas"}, {"Bryant", "Municipal Police", "Saline", "Arkansas"}, {"Waldron", "Municipal Police", "Scott", "Arkansas"}, {"Searcy County", "Sheriff", "Searcy", "Arkansas"}, {"Barling", "Municipal Police", "Sebastian", "Arkansas"}, {"De Queen", "Municipal Police", "Sevier", "Arkansas"}, {"Cherokee Village", "Municipal Police", "Sharp", "Arkansas"}, {"Forrest City", "Municipal Police", "St. Francis", "Arkansas"}, {"Mountain View", "Municipal Police", "Stone", "Arkansas"}, {"El Dorado", "Municipal Police", "Union", "Arkansas"}, {"Van Buren County", "Sheriff", "Van Buren", "Arkansas"}, {"Greenland", "Municipal Police", "Washington", "Arkansas"}, {"Judsonia", "Municipal Police", "White", "Arkansas"}, {"Augusta", "Municipal Police", "Woodruff", "Arkansas"}, {"Danville", "Municipal Police", "Yell", "Arkansas"}, {"Alameda State Police", "State Police", "Alameda", "California"}, {"Alpine", "Sheriff", "Alpine", "California"}, {"Sutter Creek", "Municipal Police", "Amador", "California"}, {"Department of Parks and Recreation: Northern Buttes", "Special Police", "Butte", "California"}, {"City of Angels", "Municipal Police", "Calaveras", "California"}, {"Williams", "Municipal Police", "Colusa", "California"}, {"HP: Contra Costa County", "State Police", "Contra Costa", "California"}, {"Crescent City", "Municipal Police", "Del Norte", "California"}, {"South Lake Tahoe", "Municipal Police", "El Dorado", "California"}, {"Highway Patrol: Fresno County", "State Police", "Fresno", "California"}, {"Willows", "Municipal Police", "Glenn", "California"}, {"Department of Parks and Recreation: North Coast Redwoods", "Special Police", "Humboldt", "California"}, {"Westmorland", "Municipal Police", "Imperial", "California"}, {"Bishop", "Municipal Police", "Inyo", "California"}, {"Stallion Springs", "Municipal Police", "Kern", "California"}, {"Avenal", "Municipal Police", "Kings", "California"}, {"HP: Lake County", "State Police", "Lake", "California"}, {"Susanville", "Municipal Police", "Lassen", "California"}, {"Los Angeles State Police", "State Police", "Los Angeles", "California"}, {"Madera", "Municipal Police", "Madera", "California"}, {"Tiburon", "Municipal Police", "Marin", "California"}, {"Mariposa County", "Sheriff", "Mariposa", "California"}, {"Willits", "Municipal Police", "Mendocino", "California"}, {"Hp: Merced County", "State Police", "Merced", "California"}, {"Alturas", "Municipal Police", "Modoc", "California"}, {"Mammoth Lakes", "Municipal Police", "Mono", "California"}, {"California State University: Monterey Bay", "Special Police", "Monterey", "California"}, {"Yountville", "Municipal Police", "Napa", "California"}, {"Truckee", "Municipal Police", "Nevada", "California"}, {"Orange State Police", "State Police", "Orange", "California"}, {"Highway Patrol: Placer County", "State Police", "Placer", "California"}, {"Plumas County", "Sheriff", "Plumas", "California"}, {"Jurupa Valley", "Municipal Police", "Riverside", "California"}, {"Calif St Police Sacramento", "State Police", "Sacramento", "California"}, {"San Juan Bautista", "Municipal Police", "San Benito", "California"}, {"San Bernardino County", "State Police", "San Bernardino", "California"}, {"Solana Beach", "Municipal Police", "San Diego", "California"}, {"Highway Patrol: San Francisco County", "State Police", "San Francisco", "California"}, {"Stockton Unified School District", "Special Police", "San Joaquin", "California"}, {"Atascadero State Hospital", "Special Police", "San Luis Obispo", "California"}, {"East Palo Alto", "Municipal Police", "San Mateo", "California"}, {"University of California: Santa Barbara", "Special Police", "Santa Barbara", "California"}, {"Santa Clara State Police", "State Police", "Santa Clara", "California"}, {"University of California: Santa Cruz", "Special Police", "Santa Cruz", "California"}, {"Redding", "Municipal Police", "Shasta", "California"}, {"Sierra", "Sheriff", "Sierra", "California"}, {"Lake Shastina", "Municipal Police", "Siskiyou", "California"}, {"Vallejo", "Municipal Police", "Solano", "California"}, {"Windsor", "Municipal Police", "Sonoma", "California"}, {"Hughson", "Municipal Police", "Stanislaus", "California"}, {"Yuba City", "Municipal Police", "Sutter", "California"}, {"Red Bluff", "Municipal Police", "Tehama", "California"}, {"Trinity County", "Sheriff", "Trinity", "California"}, {"HP: Tulare County", "State Police", "Tulare", "California"}, {"Sonora", "Municipal Police", "Tuolumne", "California"}, {"California State University: Channel Islands", "Special Police", "Ventura", "California"}, {"University of California: Davis", "Special Police", "Yolo", "California"}, {"Wheatland", "Municipal Police", "Yuba", "California"}, {"Federal Heights", "Municipal Police", "Adams", "Colorado"}, {"Alamosa", "Municipal Police", "Alamosa", "Colorado"}, {"Centennial", "Municipal Police", "Arapahoe", "Colorado"}, {"Pagosa Springs", "Municipal Police", "Archuleta", "Colorado"}, {"Baca", "Sheriff", "Baca", "Colorado"}, {"Las Animas", "Municipal Police", "Bent", "Colorado"}, {"Nederland", "Municipal Police", "Boulder", "Colorado"}, {"Broomfield", "Municipal Police", "Broomfield", "Colorado"}, {"Salida", "Municipal Police", "Chaffee", "Colorado"}, {"Cheyenne", "Sheriff", "Cheyenne", "Colorado"}, {"Georgetown", "Municipal Police", "Clear Creek", "Colorado"}, {"Manassa", "Municipal Police", "Conejos", "Colorado"}, {"Costilla", "Sheriff", "Costilla", "Colorado"}, {"Ordway", "Municipal Police", "Crowley", "Colorado"}, {"Custer", "Sheriff", "Custer", "Colorado"}, {"Cedaredge", "Municipal Police", "Delta", "Colorado"}, {"Denver", "Municipal Police", "Denver", "Colorado"}, {"Lone Tree", "Municipal Police", "Douglas", "Colorado"}, {"Gypsum", "Municipal Police", "Eagle", "Colorado"}, {"Manitou Springs", "Municipal Police", "El Paso", "Colorado"}, {"Elizabeth", "Municipal Police", "Elbert", "Colorado"}, {"Florence", "Municipal Police", "Fremont", "Colorado"}, {"New Castle", "Municipal Police", "Garfield", "Colorado"}, {"Black Hawk", "Municipal Police", "Gilpin", "Colorado"}, {"Unknown Agency: CO", "Municipal Police", "Grand", "Colorado"}, {"Crested Butte", "Municipal Police", "Gunnison", "Colorado"}, {"Hinsdale", "Sheriff", "Hinsdale", "Colorado"}, {"La Veta", "Municipal Police", "Huerfano", "Colorado"}, {"Jackson", "Sheriff", "Jackson", "Colorado"}, {"Mountain View", "Municipal Police", "Jefferson", "Colorado"}, {"Burlington", "Municipal Police", "Kit Carson", "Colorado"}, {"Ignacio", "Municipal Police", "La Plata", "Colorado"}, {"Lake", "Sheriff", "Lake", "Colorado"}, {"Loveland", "Municipal Police", "Larimer", "Colorado"}, {"Trinidad", "Municipal Police", "Las Animas", "Colorado"}, {"Limon", "Municipal Police", "Lincoln", "Colorado"}, {"Sterling", "Municipal Police", "Logan", "Colorado"}, {"Palisade", "Municipal Police", "Mesa", "Colorado"}, {"Mineral", "Sheriff", "Mineral", "Colorado"}, {"Craig", "Municipal Police", "Moffat", "Colorado"}, {"Cortez", "Municipal Police", "Montezuma", "Colorado"}, {"Olathe", "Municipal Police", "Montrose", "Colorado"}, {"Log Lane Village", "Municipal Police", "Morgan", "Colorado"}, {"Rocky Ford", "Municipal Police", "Otero", "Colorado"}, {"Alma", "Municipal Police", "Park", "Colorado"}, {"Haxtun", "Municipal Police", "Phillips", "Colorado"}, {"Snowmass Village", "Municipal Police", "Pitkin", "Colorado"}, {"Lamar", "Municipal Police", "Prowers", "Colorado"}, {"Pueblo", "Municipal Police", "Pueblo", "Colorado"}, {"Rangely", "Municipal Police", "Rio Blanco", "Colorado"}, {"Del Norte", "Municipal Police", "Rio Grande", "Colorado"}, {"Hayden", "Municipal Police", "Routt", "Colorado"}, {"Center", "Municipal Police", "Saguache", "Colorado"}, {"San Juan County", "Sheriff", "San Juan", "Colorado"}, {"San Miguel", "Sheriff", "San Miguel", "Colorado"}, {"Sedgwick", "Sheriff", "Sedgwick", "Colorado"}, {"Frisco", "Municipal Police", "Summit", "Colorado"}, {"Cripple Creek", "Municipal Police", "Teller", "Colorado"}, {"Akron", "Municipal Police", "Washington", "Colorado"}, {"Hudson", "Municipal Police", "Weld", "Colorado"}, {"Yuma County", "Sheriff", "Yuma", "Colorado"}, {"Wilton", "Municipal Police", "Fairfield", "Connecticut"}, {"Windsor Locks", "Municipal Police", "Hartford", "Connecticut"}, {"Winchester", "Municipal Police", "Litchfield", "Connecticut"}, {"Old Saybrook", "Municipal Police", "Middlesex", "Connecticut"}, {"Connecticut State Police", "State Police", "New Haven", "Connecticut"}, {"Groton Town", "Municipal Police", "New London", "Connecticut"}, {"University of Connecticut: Storrs", "Special Police", "Tolland", "Connecticut"}, {"Willimantic", "Municipal Police", "Windham", "Connecticut"}, {"State Police: Kent County", "State Police", "Kent", "Delaware"}, {"State Police: New Castle County", "State Police", "New Castle", "Delaware"}, {"State Police: Sussex County", "State Police", "Sussex", "Delaware"}, {"Metro Transit Police", "Special Police", "District of Columbia", "District of Columbia"}, {"Highway Patrol: Alachua County", "State Police", "Alachua", "Florida"}, {"Macclenny", "Municipal Police", "Baker", "Florida"}, {"Highway Patrol: Bay County", "State Police", "Bay", "Florida"}, {"Starke", "Municipal Police", "Bradford", "Florida"}, {"Highway Patrol: Brevard County", "State Police", "Brevard", "Florida"}, {"Broward Highway Patrol", "State Police", "Broward", "Florida"}, {"Blountstown", "Municipal Police", "Calhoun", "Florida"}, {"Punta Gorda", "Municipal Police", "Charlotte", "Florida"}, {"Inverness", "Municipal Police", "Citrus", "Florida"}, {"Orange Park", "Municipal Police", "Clay", "Florida"}, {"Highway Patrol: Collier County", "State Police", "Collier", "Florida"}, {"Lake City", "Municipal Police", "Columbia", "Florida"}, {"Golden Beach", "Municipal Police", "Dade", "Florida"}, {"Highway Patrol: Desoto County", "State Police", "De Soto", "Florida"}, {"Cross City", "Municipal Police", "Dixie", "Florida"}, {"Highway Patrol: Duval County", "State Police", "Duval", "Florida"}, {"University of West Florida", "Special Police", "Escambia", "Florida"}, {"Flagler Beach", "Municipal Police", "Flagler", "Florida"}, {"Highway Patrol: Franklin County", "State Police", "Franklin", "Florida"}, {"Gretna", "Municipal Police", "Gadsden", "Florida"}, {"Trenton", "Municipal Police", "Gilchrist", "Florida"}, {"Highway Patrol: Glades County", "State Police", "Glades", "Florida"}, {"Gulf County", "Sheriff", "Gulf", "Florida"}, {"White Springs", "Municipal Police", "Hamilton", "Florida"}, {"Zolfo Springs", "Municipal Police", "Hardee", "Florida"}, {"Seminole Indian", "Tribal Police", "Hendry", "Florida"}, {"Brooksville", "Municipal Police", "Hernando", "Florida"}, {"Highway Patrol: Highlands County", "State Police", "Highlands", "Florida"}, {"Highway Patrol: Hillsborough County", "State Police", "Hillsborough", "Florida"}, {"Holmes", "Municipal Police", "Holmes", "Florida"}, {"Highway Patrol: Indian River County", "State Police", "Indian River", "Florida"}, {"Highway Patrol: Jackson County", "State Police", "Jackson", "Florida"}, {"Jefferson Highway Patrol", "State Police", "Jefferson", "Florida"}, {"Lafayette County", "Sheriff", "Lafayette", "Florida"}, {"Highway Patrol: Lake County", "State Police", "Lake", "Florida"}, {"Highway Patrol: Lee County", "State Police", "Lee", "Florida"}, {"Highway Patrol: Leon County", "State Police", "Leon", "Florida"}, {"Williston", "Municipal Police", "Levy", "Florida"}, {"Highway Patrol: Liberty County", "State Police", "Liberty", "Florida"}, {"Greenville", "Municipal Police", "Madison", "Florida"}, {"Highway Patrol: Manatee County", "State Police", "Manatee", "Florida"}, {"Highway Patrol: Marion County", "State Police", "Marion", "Florida"}, {"Highway Patrol: Martin County", "State Police", "Martin", "Florida"}, {"Miami Gardens", "Municipal Police", "Miami-Dade", "Florida"}, {"Highway Patrol: Monroe County", "State Police", "Monroe", "Florida"}, {"Fernandina Beach", "Municipal Police", "Nassau", "Florida"}, {"Highway Patrol: Okaloosa County", "State Police", "Okaloosa", "Florida"}, {"Okeechobee", "Municipal Police", "Okeechobee", "Florida"}, {"Highway Patrol: Orange County", "State Police", "Orange", "Florida"}, {"Highway Patrol: Osceola County", "State Police", "Osceola", "Florida"}, {"Wellington", "Municipal Police", "Palm Beach", "Florida"}, {"Highway Patrol: Pasco County", "State Police", "Pasco", "Florida"}, {"Highway Patrol: Pinellas County", "Municipal Police", "Pinellas", "Florida"}, {"Highway Patrol: Polk County", "State Police", "Polk", "Florida"}, {"Interlachen", "Municipal Police", "Putnam", "Florida"}, {"Gulf Breeze", "Municipal Police", "Santa Rosa", "Florida"}, {"Highway Patrol: Sarasota County", "State Police", "Sarasota", "Florida"}, {"Highway Patrol: Seminole County", "State Police", "Seminole", "Florida"}, {"Highway Patrol: St. Johns County", "State Police", "St. Johns", "Florida"}, {"Highway Patrol: St. Lucie County", "State Police", "St. Lucie", "Florida"}, {"Wildwood", "Municipal Police", "Sumter", "Florida"}, {"Live Oak", "Municipal Police", "Suwannee", "Florida"}, {"Perry", "Municipal Police", "Taylor", "Florida"}, {"Union County", "Sheriff", "Union", "Florida"}, {"Highway Patrol: Volusia County", "State Police", "Volusia", "Florida"}, {"Wakulla County", "Sheriff", "Wakulla", "Florida"}, {"De Funiak Springs", "Municipal Police", "Walton", "Florida"}, {"Chipley", "Municipal Police", "Washington", "Florida"}, {"Baxley", "Municipal Police", "Appling", "Georgia"}, {"Pearson", "Municipal Police", "Atkinson", "Georgia"}, {"Alma", "Municipal Police", "Bacon", "Georgia"}, {"Baker", "Sheriff", "Baker", "Georgia"}, {"Milledgeville", "Municipal Police", "Baldwin", "Georgia"}, {"Maysville", "Municipal Police", "Banks", "Georgia"}, {"Statham", "Municipal Police", "Barrow", "Georgia"}, {"Adairsville", "Municipal Police", "Bartow", "Georgia"}, {"Fitzgerald", "Municipal Police", "Ben Hill", "Georgia"}, {"Ray City", "Municipal Police", "Berrien", "Georgia"}, {"Macon", "Municipal Police", "Bibb", "Georgia"}, {"Cochran", "Municipal Police", "Bleckley", "Georgia"}, {"Hoboken", "Municipal Police", "Brantley", "Georgia"}, {"Quitman", "Municipal Police", "Brooks", "Georgia"}, {"Richmond Hill", "Municipal Police", "Bryan", "Georgia"}, {"Brooklet", "Municipal Police", "Bulloch", "Georgia"}, {"Sardis", "Municipal Police", "Burke", "Georgia"}, {"Jackson", "Municipal Police", "Butts", "Georgia"}, {"Leary", "Municipal Police", "Calhoun", "Georgia"}, {"Woodbine", "Municipal Police", "Camden", "Georgia"}, {"Metter", "Municipal Police", "Candler", "Georgia"}, {"University of West Georgia", "Special Police", "Carroll", "Georgia"}, {"Ringgold", "Municipal Police", "Catoosa", "Georgia"}, {"Charlton", "Sheriff", "Charlton", "Georgia"}, {"Savannah State Universit", "Special Police", "Chatham", "Georgia"}, {"Chattahoochee", "Sheriff", "Chattahoochee", "Georgia"}, {"Summerville", "Municipal Police", "Chattooga", "Georgia"}, {"Holly Springs", "Municipal Police", "Cherokee", "Georgia"}, {"Clarke", "Municipal Police", "Clarke", "Georgia"}, {"Fort Gaines", "Municipal Police", "Clay", "Georgia"}, {"Lake City", "Municipal Police", "Clayton", "Georgia"}, {"Homerville", "Municipal Police", "Clinch", "Georgia"}, {"Powder Springs", "Municipal Police", "Cobb", "Georgia"}, {"Nicholls", "Municipal Police", "Coffee", "Georgia"}, {"Doerun", "Municipal Police", "Colquitt", "Georgia"}, {"Harlem", "Municipal Police", "Columbia", "Georgia"}, {"Sparks", "Municipal Police", "Cook", "Georgia"}, {"Grantville", "Municipal Police", "Coweta", "Georgia"}, {"Crawford County", "Sheriff", "Crawford", "Georgia"}, {"Cordele", "Municipal Police", "Crisp", "Georgia"}, {"Trenton", "Municipal Police", "Dade", "Georgia"}, {"Dawson County", "Sheriff", "Dawson", "Georgia"}, {"Brook Haven", "Municipal Police", "De Kalb", "Georgia"}, {"Bainbridge", "Municipal Police", "Decatur", "Georgia"}, {"Eastman", "Municipal Police", "Dodge", "Georgia"}, {"Unadilla", "Municipal Police", "Dooly", "Georgia"}, {"Dougherty County", "County Police", "Dougherty", "Georgia"}, {"Douglasville", "Municipal Police", "Douglas", "Georgia"}, {"Blakely", "Municipal Police", "Early", "Georgia"}, {"Echols", "Sheriff", "Echols", "Georgia"}, {"Rincon", "Municipal Police", "Effingham", "Georgia"}, {"Elberton", "Municipal Police", "Elbert", "Georgia"}, {"Swainsboro", "Municipal Police", "Emanuel", "Georgia"}, {"Claxton", "Municipal Police", "Evans", "Georgia"}, {"Mccaysville", "Municipal Police", "Fannin", "Georgia"}, {"Tyrone", "Municipal Police", "Fayette", "Georgia"}, {"Cave Spring", "Municipal Police", "Floyd", "Georgia"}, {"Cumming", "Municipal Police", "Forsyth", "Georgia"}, {"Canon", "Municipal Police", "Franklin", "Georgia"}, {"Atlanta", "Municipal Police", "Fulton", "Georgia"}, {"Gilmer", "Sheriff", "Gilmer", "Georgia"}, {"Glascock", "Sheriff", "Glascock", "Georgia"}, {"Glynn County", "County Police", "Glynn", "Georgia"}, {"Calhoun", "Municipal Police", "Gordon", "Georgia"}, {"Cairo", "Municipal Police", "Grady", "Georgia"}, {"Greene County", "County Police", "Greene", "Georgia"}, {"Suwanee", "Municipal Police", "Gwinnett", "Georgia"}, {"Baldwin", "Municipal Police", "Habersham", "Georgia"}, {"Oakwood", "Municipal Police", "Hall", "Georgia"}, {"Sparta", "Municipal Police", "Hancock", "Georgia"}, {"Buchanan", "Municipal Police", "Haralson", "Georgia"}, {"Hamilton", "Municipal Police", "Harris", "Georgia"}, {"Hartwell", "Municipal Police", "Hart", "Georgia"}, {"Heard", "Sheriff", "Heard", "Georgia"}, {"Henry County", "County Police", "Henry", "Georgia"}, {"Centerville", "Municipal Police", "Houston", "Georgia"}, {"Ocilla", "Municipal Police", "Irwin", "Georgia"}, {"Braselton", "Municipal Police", "Jackson", "Georgia"}, {"Monticello", "Municipal Police", "Jasper", "Georgia"}, {"Hazlehurst", "Municipal Police", "Jeff Davis", "Georgia"}, {"Wrens", "Municipal Police", "Jefferson", "Georgia"}, {"Millen", "Municipal Police", "Jenkins", "Georgia"}, {"Johnson", "Sheriff", "Johnson", "Georgia"}, {"Gray", "Municipal Police", "Jones", "Georgia"}, {"Barnesville", "Municipal Police", "Lamar", "Georgia"}, {"Lakeland", "Municipal Police", "Lanier", "Georgia"}, {"East Dublin", "Municipal Police", "Laurens", "Georgia"}, {"Smithville", "Municipal Police", "Lee", "Georgia"}, {"Hinesville", "Municipal Police", "Liberty", "Georgia"}, {"Lincolnton", "Municipal Police", "Lincoln", "Georgia"}, {"Long County", "Sheriff", "Long", "Georgia"}, {"Valdosta State Universit", "Special Police", "Lowndes", "Georgia"}, {"Lumpkin County", "Sheriff", "Lumpkin", "Georgia"}, {"Oglethorpe", "Municipal Police", "Macon", "Georgia"}, {"Comer", "Municipal Police", "Madison", "Georgia"}, {"Buena Vista", "Municipal Police", "Marion", "Georgia"}, {"Thomson", "Municipal Police", "McDuffie", "Georgia"}, {"Darien", "Municipal Police", "McIntosh", "Georgia"}, {"Woodbury", "Municipal Police", "Meriwether", "Georgia"}, {"Colquitt", "Municipal Police", "Miller", "Georgia"}, {"Pelham", "Municipal Police", "Mitchell", "Georgia"}, {"Forsyth", "Municipal Police", "Monroe", "Georgia"}, {"Montgomery", "Sheriff", "Montgomery", "Georgia"}, {"Madison", "Municipal Police", "Morgan", "Georgia"}, {"Chatsworth", "Municipal Police", "Murray", "Georgia"}, {"Columbus", "Municipal Police", "Muscogee", "Georgia"}, {"Porterdale", "Municipal Police", "Newton", "Georgia"}, {"Watkinsville", "Municipal Police", "Oconee", "Georgia"}, {"Oglethorpe", "Sheriff", "Oglethorpe", "Georgia"}, {"Dallas", "Municipal Police", "Paulding", "Georgia"}, {"Byron", "Municipal Police", "Peach", "Georgia"}, {"Jasper", "Municipal Police", "Pickens", "Georgia"}, {"Blackshear", "Municipal Police", "Pierce", "Georgia"}, {"Pike County", "Sheriff", "Pike", "Georgia"}, {"Polk County", "County Police", "Polk", "Georgia"}, {"Hawkinsville", "Municipal Police", "Pulaski", "Georgia"}, {"Eatonton", "Municipal Police", "Putnam", "Georgia"}, {"Georgetown", "Municipal Police", "Quitman", "Georgia"}, {"Clayton", "Municipal Police", "Rabun", "Georgia"}, {"Cuthbert", "Municipal Police", "Randolph", "Georgia"}, {"Medical College of Georgia", "Special Police", "Richmond", "Georgia"}, {"Conyers", "Municipal Police", "Rockdale", "Georgia"}, {"Ellaville", "Municipal Police", "Schley", "Georgia"}, {"Newington", "Municipal Police", "Screven", "Georgia"}, {"Donalsonville", "Municipal Police", "Seminole", "Georgia"}, {"Griffin", "Municipal Police", "Spalding", "Georgia"}, {"Toccoa", "Municipal Police", "Stephens", "Georgia"}, {"Richland", "Municipal Police", "Stewart", "Georgia"}, {"Americus", "Municipal Police", "Sumter", "Georgia"}, {"Talbot County", "Sheriff", "Talbot", "Georgia"}, {"Taliaferro", "Municipal Police", "Taliaferro", "Georgia"}, {"Reidsville", "Municipal Police", "Tattnall", "Georgia"}, {"Reynolds", "Municipal Police", "Taylor", "Georgia"}, {"Milan", "Municipal Police", "Telfair", "Georgia"}, {"Dawson", "Municipal Police", "Terrell", "Georgia"}, {"Barwick", "Municipal Police", "Thomas", "Georgia"}, {"Tifton", "Municipal Police", "Tift", "Georgia"}, {"Vidalia", "Municipal Police", "Toombs", "Georgia"}, {"Towns County", "Sheriff", "Towns", "Georgia"}, {"Soperton", "Municipal Police", "Treutlen", "Georgia"}, {"West Point", "Municipal Police", "Troup", "Georgia"}, {"Ashburn", "Municipal Police", "Turner", "Georgia"}, {"Jeffersonville", "Municipal Police", "Twiggs", "Georgia"}, {"Blairsville", "Municipal Police", "Union", "Georgia"}, {"Thomaston", "Municipal Police", "Upson", "Georgia"}, {"Linwood", "Municipal Police", "Walker", "Georgia"}, {"Social Circle", "Special Police", "Walton", "Georgia"}, {"Waycross", "Municipal Police", "Ware", "Georgia"}, {"Warrenton", "Municipal Police", "Warren", "Georgia"}, {"Davisboro", "Municipal Police", "Washington", "Georgia"}, {"Jesup", "Municipal Police", "Wayne", "Georgia"}, {"Webster", "Sheriff", "Webster", "Georgia"}, {"Glenwood", "Municipal Police", "Wheeler", "Georgia"}, {"Helen", "Municipal Police", "White", "Georgia"}, {"Dalton", "Municipal Police", "Whitfield", "Georgia"}, {"Rochelle", "Municipal Police", "Wilcox", "Georgia"}, {"Washington", "Municipal Police", "Wilkes", "Georgia"}, {"Gordon", "Municipal Police", "Wilkinson", "Georgia"}, {"Warwick", "Municipal Police", "Worth", "Georgia"}, {"Hilo", "Municipal Police", "Hawaii", "Hawaii"}, {"Honolulu County", "Sheriff", "Honolulu", "Hawaii"}, {"Kauai County", "Sheriff", "Kauai", "Hawaii"}, {"Maui County", "Sheriff", "Maui", "Hawaii"}, {"Meridian", "Municipal Police", "Ada", "Idaho"}, {"Adams", "Sheriff", "Adams", "Idaho"}, {"Pocatello", "Municipal Police", "Bannock", "Idaho"}, {"Montpelier", "Municipal Police", "Bear Lake", "Idaho"}, {"St. Maries", "Municipal Police", "Benewah", "Idaho"}, {"Shelley", "Municipal Police", "Bingham", "Idaho"}, {"Bellevue", "Municipal Police", "Blaine", "Idaho"}, {"Idaho State Police", "State Police", "Boise", "Idaho"}, {"Sandpoint", "Municipal Police", "Bonner", "Idaho"}, {"Idaho Falls", "Municipal Police", "Bonneville", "Idaho"}, {"Boundary", "Sheriff", "Boundary", "Idaho"}, {"Butte", "Sheriff", "Butte", "Idaho"}, {"Camas", "Sheriff", "Camas", "Idaho"}, {"Wilder", "Municipal Police", "Canyon", "Idaho"}, {"Soda Springs", "Municipal Police", "Caribou", "Idaho"}, {"Burley", "Municipal Police", "Cassia", "Idaho"}, {"Clark", "Sheriff", "Clark", "Idaho"}, {"Orofino", "Municipal Police", "Clearwater", "Idaho"}, {"Custer", "Sheriff", "Custer", "Idaho"}, {"Mountain Home", "Municipal Police", "Elmore", "Idaho"}, {"Preston", "Municipal Police", "Franklin", "Idaho"}, {"Fremont", "Sheriff", "Fremont", "Idaho"}, {"Emmett", "Municipal Police", "Gem", "Idaho"}, {"Gooding", "Municipal Police", "Gooding", "Idaho"}, {"Grangeville", "Municipal Police", "Idaho", "Idaho"}, {"Rigby", "Municipal Police", "Jefferson", "Idaho"}, {"Jerome", "Municipal Police", "Jerome", "Idaho"}, {"Spirit Lake", "Municipal Police", "Kootenai", "Idaho"}, {"Moscow", "Municipal Police", "Latah", "Idaho"}, {"Salmon", "Municipal Police", "Lemhi", "Idaho"}, {"Lewis", "Sheriff", "Lewis", "Idaho"}, {"Lincoln", "Sheriff", "Lincoln", "Idaho"}, {"Rexburg", "Municipal Police", "Madison", "Idaho"}, {"Heyburn", "Municipal Police", "Minidoka", "Idaho"}, {"Lewiston", "Municipal Police", "Nez Perce", "Idaho"}, {"Oneida County", "Sheriff", "Oneida", "Idaho"}, {"Homedale", "Municipal Police", "Owyhee", "Idaho"}, {"Payette", "Municipal Police", "Payette", "Idaho"}, {"American Falls", "Municipal Police", "Power", "Idaho"}, {"Bureau of Narcotics: Region 4", "Special Police", "Repressed", "Idaho"}, {"Smelterville", "Municipal Police", "Shoshone", "Idaho"}, {"Teton", "Sheriff", "Teton", "Idaho"}, {"Twin Falls", "Municipal Police", "Twin Falls", "Idaho"}, {"McCall", "Municipal Police", "Valley", "Idaho"}, {"Weiser", "Municipal Police", "Washington", "Idaho"}, {"Quincy", "Municipal Police", "Adams", "Illinois"}, {"Cairo", "Municipal Police", "Alexander", "Illinois"}, {"Greenville", "Municipal Police", "Bond", "Illinois"}, {"Belvidere", "Municipal Police", "Boone", "Illinois"}, {"Mount Sterling", "Municipal Police", "Brown", "Illinois"}, {"Spring Valley", "Municipal Police", "Bureau", "Illinois"}, {"Calhoun", "Sheriff", "Calhoun", "Illinois"}, {"Carroll", "Sheriff", "Carroll", "Illinois"}, {"Beardstown", "Municipal Police", "Cass", "Illinois"}, {"Fisher", "Municipal Police", "Champaign", "Illinois"}, {"Taylorville", "Municipal Police", "Christian", "Illinois"}, {"Martinsville", "Municipal Police", "Clark", "Illinois"}, {"Flora", "Municipal Police", "Clay", "Illinois"}, {"Trenton", "Municipal Police", "Clinton", "Illinois"}, {"Mattoon", "Municipal Police", "Coles", "Illinois"}, {"Chicago", "Municipal Police", "Cook", "Illinois"}, {"Robinson", "Municipal Police", "Crawford", "Illinois"}, {"Neoga", "Municipal Police", "Cumberland", "Illinois"}, {"Clinton", "Municipal Police", "De Witt", "Illinois"}, {"De Kalb", "Municipal Police", "DeKalb", "Illinois"}, {"Arthur", "Municipal Police", "Douglas", "Illinois"}, {"Oakbrook Terrace", "Municipal Police", "DuPage", "Illinois"}, {"Paris", "Municipal Police", "Edgar", "Illinois"}, {"State Police: INV District 12", "State Police", "Effingham", "Illinois"}, {"Fayette", "Sheriff", "Fayette", "Illinois"}, {"Ford", "Sheriff", "Ford", "Illinois"}, {"West Frankfort", "Municipal Police", "Franklin", "Illinois"}, {"Farmington", "Municipal Police", "Fulton", "Illinois"}, {"Shawneetown", "Municipal Police", "Gallatin", "Illinois"}, {"White Hall", "Municipal Police", "Greene", "Illinois"}, {"Morris", "Municipal Police", "Grundy", "Illinois"}, {"Hancock", "Sheriff", "Hancock", "Illinois"}, {"Hardin", "Sheriff", "Hardin", "Illinois"}, {"Henderson", "Sheriff", "Henderson", "Illinois"}, {"Green Rock", "Municipal Police", "Henry", "Illinois"}, {"Watseka", "Municipal Police", "Iroquois", "Illinois"}, {"Murphysboro", "Municipal Police", "Jackson", "Illinois"}, {"Jasper", "Sheriff", "Jasper", "Illinois"}, {"Mount Vernon", "Municipal Police", "Jefferson", "Illinois"}, {"Jerseyville", "Municipal Police", "Jersey", "Illinois"}, {"Jo Daviess", "Sheriff", "Jo Daviess", "Illinois"}, {"Johnson", "Sheriff", "Johnson", "Illinois"}, {"State Police: District 2", "State Police", "Kane", "Illinois"}, {"Manteno", "Municipal Police", "Kankakee", "Illinois"}, {"Plano", "Municipal Police", "Kendall", "Illinois"}, {"Galesburg", "Municipal Police", "Knox", "Illinois"}, {"Streator", "Municipal Police", "La Salle", "Illinois"}, {"Lincolnshire", "Municipal Police", "Lake", "Illinois"}, {"Lawrence", "Sheriff", "Lawrence", "Illinois"}, {"Dixon", "Municipal Police", "Lee", "Illinois"}, {"Logan", "Sheriff", "Logan", "Illinois"}, {"Mount Zion", "Municipal Police", "Macon", "Illinois"}, {"Virden", "Municipal Police", "Macoupin", "Illinois"}, {"State Police: District 11", "State Police", "Madison", "Illinois"}, {"Salem", "Municipal Police", "Marion", "Illinois"}, {"Lacon", "Municipal Police", "Marshall", "Illinois"}, {"Havana", "Municipal Police", "Mason", "Illinois"}, {"Metropolis", "Municipal Police", "Massac", "Illinois"}, {"Macomb", "Municipal Police", "McDonough", "Illinois"}, {"Woodstock", "Municipal Police", "McHenry", "Illinois"}, {"Illinois State University", "Special Police", "McLean", "Illinois"}, {"Petersburg", "Municipal Police", "Menard", "Illinois"}, {"Aledo", "Municipal Police", "Mercer", "Illinois"}, {"Waterloo", "Municipal Police", "Monroe", "Illinois"}, {"Litchfield", "Municipal Police", "Montgomery", "Illinois"}, {"Jacksonville", "Municipal Police", "Morgan", "Illinois"}, {"Moultrie", "Sheriff", "Moultrie", "Illinois"}, {"Byron", "Municipal Police", "Ogle", "Illinois"}, {"State Police: INV District 8", "State Police", "Peoria", "Illinois"}, {"Pinckneyville", "Municipal Police", "Perry", "Illinois"}, {"Pike", "Sheriff", "Pike", "Illinois"}, {"Pope", "Sheriff", "Pope", "Illinois"}, {"State Police: District 22", "State Police", "Pulaski", "Illinois"}, {"Putnam", "Sheriff", "Putnam", "Illinois"}, {"Coulterville", "Municipal Police", "Randolph", "Illinois"}, {"Olney", "Municipal Police", "Richland", "Illinois"}, {"Hillsdale", "Municipal Police", "Rock Island", "Illinois"}, {"Harrisburg", "Municipal Police", "Saline", "Illinois"}, {"Springfield", "Municipal Police", "Sangamon", "Illinois"}, {"Schuyler", "Sheriff", "Schuyler", "Illinois"}, {"Scott", "Sheriff", "Scott", "Illinois"}, {"Shelby", "Sheriff", "Shelby", "Illinois"}, {"Fairview Heights", "Municipal Police", "St. Clair", "Illinois"}, {"Freeport", "Municipal Police", "Stephenson", "Illinois"}, {"Pekin", "Municipal Police", "Tazewell", "Illinois"}, {"Anna", "Municipal Police", "Union", "Illinois"}, {"Danville", "Municipal Police", "Vermilion", "Illinois"}, {"Mount Carmel", "Municipal Police", "Wabash", "Illinois"}, {"Monmouth", "Municipal Police", "Warren", "Illinois"}, {"Washington", "Sheriff", "Washington", "Illinois"}, {"Fairfield", "Municipal Police", "Wayne", "Illinois"}, {"State Police: INV District 19", "State Police", "White", "Illinois"}, {"Sterling", "Municipal Police", "Whiteside", "Illinois"}, {"State Police: District 5", "State Police", "Will", "Illinois"}, {"State Police: INV District 13", "State Police", "Williamson", "Illinois"}, {"Machesney Park", "Municipal Police", "Winnebago", "Illinois"}, {"Woodford", "Sheriff", "Woodford", "Illinois"}, {"State Police: Adams County", "State Police", "Adams", "Indiana"}, {"State Police: Allen County", "State Police", "Allen", "Indiana"}, {"State Police: Bartholomew County", "State Police", "Bartholomew", "Indiana"}, {"State Police: Benton County", "State Police", "Benton", "Indiana"}, {"State Police: Blackford County", "State Police", "Blackford", "Indiana"}, {"State Police: Boone County", "State Police", "Boone", "Indiana"}, {"State Police: Brown County", "State Police", "Brown", "Indiana"}, {"State Police: Carroll County", "State Police", "Carroll", "Indiana"}, {"State Police: Cass County", "State Police", "Cass", "Indiana"}, {"State Police: Clark County", "State Police", "Clark", "Indiana"}, {"State Police: Clay County", "State Police", "Clay", "Indiana"}, {"State Police: Clinton County", "State Police", "Clinton", "Indiana"}, {"State Police: Crawford County", "State Police", "Crawford", "Indiana"}, {"State Police: Daviess County", "State Police", "Daviess", "Indiana"}, {"State Police: De Kalb County", "State Police", "De Kalb", "Indiana"}, {"State Police: Dearborn County", "State Police", "Dearborn", "Indiana"}, {"State Police: Decatur County", "State Police", "Decatur", "Indiana"}, {"State Police: Delaware County", "State Police", "Delaware", "Indiana"}, {"State Police: Dubois County", "State Police", "Dubois", "Indiana"}, {"State Police: Elkhart County", "State Police", "Elkhart", "Indiana"}, {"State Police: Fayette County", "State Police", "Fayette", "Indiana"}, {"State Police: Floyd County", "State Police", "Floyd", "Indiana"}, {"State Police: Fountain County", "State Police", "Fountain", "Indiana"}, {"State Police: Franklin County", "State Police", "Franklin", "Indiana"}, {"State Police: Fulton County", "State Police", "Fulton", "Indiana"}, {"State Police: Gibson County", "State Police", "Gibson", "Indiana"}, {"State Police: Grant County", "State Police", "Grant", "Indiana"}, {"State Police: Greene County", "State Police", "Greene", "Indiana"}, {"State Police: Hamilton County", "State Police", "Hamilton", "Indiana"}, {"State Police: Hancock County", "State Police", "Hancock", "Indiana"}, {"State Police: Harrison County", "State Police", "Harrison", "Indiana"}, {"State Police: Hendricks County", "State Police", "Hendricks", "Indiana"}, {"State Police: Henry County", "State Police", "Henry", "Indiana"}, {"State Police: Howard County", "State Police", "Howard", "Indiana"}, {"State Police: Huntington County", "State Police", "Huntington", "Indiana"}, {"State Police: Jackson County", "State Police", "Jackson", "Indiana"}, {"State Police: Jasper County", "State Police", "Jasper", "Indiana"}, {"State Police: Jay County", "State Police", "Jay", "Indiana"}, {"State Police: Jefferson County", "State Police", "Jefferson", "Indiana"}, {"State Police: Jennings County", "State Police", "Jennings", "Indiana"}, {"State Police: Johnson County", "State Police", "Johnson", "Indiana"}, {"State Police: Knox County", "State Police", "Knox", "Indiana"}, {"State Police: Kosciusko County", "State Police", "Kosciusko", "Indiana"}, {"State Police: La Porte County", "State Police", "La Porte", "Indiana"}, {"State Police: Lagrange County", "State Police", "Lagrange", "Indiana"}, {"State Police: Lake County", "State Police", "Lake", "Indiana"}, {"State Police: Lawrence County", "State Police", "Lawrence", "Indiana"}, {"State Police: Madison County", "State Police", "Madison", "Indiana"}, {"Indiana State Police", "State Police", "Marion", "Indiana"}, {"State Police: Marshall County", "State Police", "Marshall", "Indiana"}, {"State Police: Martin County", "State Police", "Martin", "Indiana"}, {"State Police: Miami County", "State Police", "Miami", "Indiana"}, {"State Police: Monroe County", "State Police", "Monroe", "Indiana"}, {"State Police: Montgomery County", "State Police", "Montgomery", "Indiana"}, {"State Police: Morgan County", "State Police", "Morgan", "Indiana"}, {"State Police: Newton County", "State Police", "Newton", "Indiana"}, {"State Police: Noble County", "State Police", "Noble", "Indiana"}, {"State Police: Ohio County", "State Police", "Ohio", "Indiana"}, {"State Police: Orange County", "State Police", "Orange", "Indiana"}, {"State Police: Owen County", "State Police", "Owen", "Indiana"}, {"State Police: Parke County", "State Police", "Parke", "Indiana"}};
    private Date[] dateRange = {new Date("1/1/1984"), new Date("12/31/2013")};
    private int[] perpAgeRange = {16, 65};
    private int[] victimAgeRange = {0, 100};
    private String[] relationship = { "stranger",
            "aquaintance",
            "dependent",
            "parent",
            "sibling",
            "colleague",
            "friend",
            "other relative"};

    public String randomEthnicity()
    {
        return mEthnicity[ThreadLocalRandom.current().nextInt(mEthnicity.length)];
    }

    public String[] randomLocationAndAgency()
    {
        return mLocationAndAgency[ThreadLocalRandom.current().nextInt(mLocationAndAgency.length)];
    }

    public String randomCaseStates()
    {
        return mCaseStates[ThreadLocalRandom.current().nextInt(mCaseStates.length)];
    }

    public String randomCrimes()
    {
        int i = ThreadLocalRandom.current().nextInt(4);
        if (i == 0)
        {
            return mCrimes[0];
        }
        return mCrimes[ThreadLocalRandom.current().nextInt(mCrimes.length)];
    }

    public String randomGender()
    {
        return mGender[ThreadLocalRandom.current().nextInt(mGender.length)];
    }
    
    public String randomWeapon()
    {
        return weapon[ThreadLocalRandom.current().nextInt(weapon.length)];
    }

    public Date randomDateAndTime()
    {
        return new Date(ThreadLocalRandom.current().nextLong(dateRange[0].getTime(), dateRange[1].getTime()));
    }

    public int randomPerpAge()
    {
        return ThreadLocalRandom.current().nextInt(perpAgeRange[0], perpAgeRange[1] + 1);
    }

    public int randomVictimAge()
    {
        return ThreadLocalRandom.current().nextInt(victimAgeRange[0], victimAgeRange[1] + 1);
    }

    public String randomRelationship()
    {
        return relationship[ThreadLocalRandom.current().nextInt(relationship.length)];
    }

    public int[] randomPerpAndVictimCount()
    {
        int[] result = {0, 0};
        int i = ThreadLocalRandom.current().nextInt(1, 100);
        int j = ThreadLocalRandom.current().nextInt(1, 100);
        if (i < 80 && j < 80)
        {
            result[0] = 1;
            result[1] = 1;
        }
        else if (i < 88 && j < 82)
        {
            result[0] = 2;
            result[1] = 1;
        }
        else if (i < 96 && j < 82)
        {
            result[0] = 3;
            result[1] = 1;
        }
        else if (i < 80 && j < 86)
        {
            result[0] = 1;
            result[1] = 2;
        }
        else if (i < 94 && j < 86)
        {
            result[0] = 2;
            result[1] = 2;
        }
        else if (i < 98 && j < 86)
        {
            result[0] = 3;
            result[1] = 2;
        }
        else if (i < 92 && j < 96)
        {
            result[0] = 1;
            result[1] = 3;
        }
        else if (i < 99 && j < 99)
        {
            result[0] = 2;
            result[1] = 3;
        }
        else
        {
            result[0] = 3;
            result[1] = 3;
        }

        return result;
    }

    public String randomVideoEvidenceCode()
    {
        return String.format("V%08d", ThreadLocalRandom.current().nextInt(1024 * 1024 * 32));
    }

    public String randomAudioEvidenceCode()
    {
        return String.format("A%08d", ThreadLocalRandom.current().nextInt(1024 * 1024 * 32));
    }

    public String randomPhotoEvidenceCode()
    {
        return String.format("P%08d", ThreadLocalRandom.current().nextInt(1024 * 1024 * 32));
    }

    public int[] randomEvidenceCounts()
    {
        int[] result = {0, 0, 0};

        result[0] = ThreadLocalRandom.current().nextInt(3);
        result[1] = ThreadLocalRandom.current().nextInt(3);
        result[2] = ThreadLocalRandom.current().nextInt(11);

        return result;
    }

    public Perpetrator generatePerpetrator()
    {
        Perpetrator p =  new Perpetrator();
        p.setAge(randomPerpAge());
        p.setEthnicity(randomEthnicity());
        p.setGender(randomGender());
        return p;
    }

    public Victim generateVictim()
    {
        Victim p =  new Victim();
        p.setAge(randomVictimAge());
        p.setEthnicity(randomEthnicity());
        p.setGender(randomGender());
        return p;
    }

    public Relationship generateRelationship(Victim pVictim, Perpetrator pPerp)
    {
        Relationship r = new Relationship();
        r.setPerpetrator(pPerp);
        r.setVictim(pVictim);
        r.setRelation(randomRelationship());
        return  r;
    }

    // generate random case
    public CaseRecord generateCase()
    {
        CaseRecord c = new CaseRecord();
        String[] agencyData = randomLocationAndAgency();
        c.setAgencyName(agencyData[0]);
        c.setAgencyType(agencyData[1]);
        c.setCity(agencyData[2]);
        c.setState(agencyData[3]);
        c.setDateTime(randomDateAndTime());
        c.setCrimeType(randomCrimes());
        c.setStatus(randomCaseStates());
        c.setmWeapon(randomWeapon());
        
        for(int i=0;i<randomEvidenceCounts()[0];i++){
        	c.addEvidence(randomAudioEvidenceCode());
        }
        for(int i=0;i<randomEvidenceCounts()[1];i++){
        	c.addEvidence(randomPhotoEvidenceCode());
        }
        for(int i=0;i<randomEvidenceCounts()[2];i++){
        	c.addEvidence(randomVideoEvidenceCode());
        }
        int[] perpAndVictimCount = randomPerpAndVictimCount();

        int i = 0;
        for (i = 0; i < perpAndVictimCount[0]; i++)
        {
            c.addPerpetrator(generatePerpetrator());
        }

        for(i = 0; i < perpAndVictimCount[1]; i++)
        {
            c.addVictim(generateVictim());
        }

        for (Perpetrator p: c.getAllPerpetrators())
        {
            for (Victim v: c.getAllVictims())
            {
                c.addRelationship(generateRelationship(v, p));
            }
        }

        return c;
    }

}
