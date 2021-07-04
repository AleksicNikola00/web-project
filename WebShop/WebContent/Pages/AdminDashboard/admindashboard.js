//visible : restaurants, specificRestaurant, addEditRestaurant, users, addEditUser
//userVisible : shoppers, managers, deliveryWorkers, admins

function displaySubmenu(submenu){
    $("div[name='" + submenu + "']").slideToggle(700);
}

function openDropDown(dropdown){
    if ( $("div[name='" + dropdown +"']").css('background-image').includes('angleDown.png')){
        $("div[name='" + dropdown +"']").css('background-image', 'url("../Images/angleUp.png")');
    }
    else {
        $("div[name='" + dropdown +"']").css('background-image', 'url("../Images/angleDown.png")');
    }

    $("div[name='" + dropdown + "dropdown']").slideToggle(700);
}

function changeTag(tag){
    $("button[name='tags']").removeClass('selected-tag');

    $("#" + tag).addClass('selected-tag');
}

var webShop = new Vue({
    el: '#dashboard',
    data: {
        visible : 'addEditUser',
        userVisible : 'managers',
        currentUser : {},
        receivedRestaurants : [],
        receivedUsers : [],
        users : [],
        restaurants : [
            {
                name : 'First',
                type : 'Turkish',
                rating : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24',
                status : 'OPEN',
                geoLocation : '86.3333, 100.233',
                managerId : '1',
            },
            {
                name : 'First',
                type : 'Turkish',
                rating : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24',
                status : 'OPEN',
                geoLocation : '86.3333, 100.233',
                managerId : '7',
            },
            {
                name : 'First',
                type : 'Turkish',
                rating : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24',
                status : 'OPEN',
                geoLocation : '86.3333, 100.233',
                managerId : '6',
            },
            {
                name : 'First',
                type : 'Turkish',
                rating : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24',
                status : 'OPEN',
                geoLocation : '86.3333, 100.233',
                managerId : '3',
            },
            {
                name : 'First',
                type : 'Turkish',
                rating : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24',
                status : 'OPEN',
                geoLocation : '86.3333, 100.233',
                managerId : '5',
            },
            {
                name : 'First',
                type : 'Turkish',
                rating : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24',
                status : 'OPEN',
                geoLocation : '86.3333, 100.233',
                managerId : '2',
            },
            {
                name : 'First',
                type : 'Turkish',
                rating : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24',
                status : 'OPEN',
                geoLocation : '86.3333, 100.233',
                managerId : '4',
            }
        ],
        restaurantFilterObj : {
            name : '',
            type : '',
            location : '',
            mark : 'All marks',
            ascDes : 'Ascending'
        },
        selectedRestaurant : {},
        items : [
            {
                name : 'Pizza',
                price : '1000',
                amount : '700',
                type : 'FOOD',
                unit : 'GRAM',
                description : 'Some amazing pizza that has been created by the best chefs!'
            },
            {
                name : 'Pizza',
                price : '1000',
                amount : '700',
                type : 'FOOD',
                unit : 'GRAM',
                description : 'Some amazing pizza that has been created by the best chefs!'
            },
            {
                name : 'Pizza',
                price : '1000',
                amount : '700',
                type : 'FOOD',
                unit : 'GRAM',
                description : 'Some amazing pizza that has been created by the best chefs!'
            }
        ],
        comments : [
            {
                text : 'its really amazing lads go ahead and try!',
                username : 'ProSlayerXXX',
                mark : '5'
            },
            {
                text : 'Could be better i guess but whatevs bitches are good',
                username : 'NiggaHigga',
                mark : '3'
            },
        ],
        tempRestaurant : {
            name : '',
            type : '',
            location : '',
            geoLocation : '',
            logo : '',
            managerId : '1'
        },
        allTypes : [
            'Turkish',
            'Greek',
            'Italian',
            'Pub',
            'Barbecue'
        ],
        allManagers : [
            {
                name : 'Nikola',
                surname : 'Milosavljevic',
                id : '1',
            },
            {
                name : 'Nikola',
                surname : 'Aleksic',
                id : '2',
            },
            {
                name : 'Momo',
                surname : 'Kapor',
                id : '3',
            },
            {
                name : 'Istvan',
                surname : 'Becar',
                id : '4',
            },
            {
                name : 'Maksim',
                surname : 'Uskokovic',
                id : '5',
            },
            {
                name : 'Deki',
                surname : 'Stankela',
                id : '6',
            },
            {
                name : 'Emina',
                surname : 'Jahovic',
                id : '7',
            },
            {
                name : 'Boris',
                surname : 'Dzonsonovic',
                id : '8',
            },
            {
                name : 'Kemal',
                surname : 'Monteno',
                id : '9',
            }
        ],
        receivedShoppers : [],
        shoppers : [
            {
                name : 'Nikola',
                surname : 'Milosavljevic',
                username : 'nikkiyuh',
                dateOfBirth : '1999-07-04',
                gender : 'MALE',
                collectedPoints : '1300',
                type : 'BRONZE',
                status : 'normal'
            },
            {
                name : 'Nikola',
                surname : 'Aleksic',
                username : 'otherNikky',
                dateOfBirth : '1940-07-02',
                gender : 'MALE',
                collectedPoints : '2600',
                type : 'SILVER',
                status : 'suspicious'
            },
            {
                name : 'Filler',
                surname : 'User',
                username : 'fillerUser',
                dateOfBirth : '1.1.1940',
                gender : 'MALE',
                collectedPoints : '2600',
                type : 'SILVER',
                status : 'blocked'
            },
            {
                name : 'Filler',
                surname : 'User',
                username : 'fillerUser',
                dateOfBirth : '1.1.1940',
                gender : 'MALE',
                collectedPoints : '2600',
                type : 'SILVER',
                status : 'normal'
            },
            {
                name : 'Filler',
                surname : 'User',
                username : 'fillerUser',
                dateOfBirth : '1.1.1940',
                gender : 'MALE',
                collectedPoints : '2600',
                type : 'SILVER',
                status : 'suspicious'
            },
            {
                name : 'Filler',
                surname : 'User',
                username : 'fillerUser',
                dateOfBirth : '1.1.1940',
                gender : 'MALE',
                collectedPoints : '2600',
                type : 'SILVER',
                status : 'normal'
            },
            {
                name : 'Filler',
                surname : 'User',
                username : 'fillerUser',
                dateOfBirth : '1.1.1940',
                gender : 'MALE',
                collectedPoints : '2600',
                type : 'SILVER',
                status : 'suspicious'
            },
            {
                name : 'Filler',
                surname : 'User',
                username : 'fillerUser',
                dateOfBirth : '1.1.1940',
                gender : 'MALE',
                collectedPoints : '2600',
                type : 'SILVER',
                status : 'blocked'
            },
            {
                name : 'Filler',
                surname : 'User',
                username : 'fillerUser',
                dateOfBirth : '1.1.1940',
                gender : 'MALE',
                collectedPoints : '2600',
                type : 'SILVER',
                status : 'normal'
            },
            {
                name : 'Filler',
                surname : 'User',
                username : 'fillerUser',
                dateOfBirth : '1.1.1940',
                gender : 'MALE',
                collectedPoints : '2600',
                type : 'SILVER',
                status : 'suspicious'
            }
        ],
        shopperFilterObj : {
            name : '',
            surname : '',
            username : '',
            ascDes : 'Ascending',
            points : 0,
            shopperType : 'BRONZE',
        },

        receivedManagers : [],
        managers : [
            {
                name : 'Dzoni',
                surname : 'Stagod',
                dateOfBirth : '1941-05-26',
                gender : 'MALE',
                restaurant : 'Ciao',
                username : 'nikkiyyuh'
            }
        ],
        managerFilterObj : {
            name : '',
            surname : '',
            username : '',
            ascDes : 'Ascending'
        },

        receivedDeliveryWorkers : [],
        deliveryWorkers : [
            {
                name : 'Nikola',
                surname : 'Milosavljevic',
                username : 'nikkiyuh',
                dateOfBirth : '4.7.1999.',
                gender : 'MALE'
            }
        ],
        deliveryWorkerFilterObj : {
            name : '',
            surname : '',
            username : '',
            ascDes : 'Ascending'
        },

        receivedAdmins : [],
        admins : [
            {
                name : 'Nikola',
                surname : 'Milosavljevic',
                dateOfBirth : '4.7.1999.',
                username : 'nikkiyuh',
                gender : 'MALE'
            }
        ],
        adminFilterObj : {
            name : '',
            surname : '',
            username : '',
            ascDes : 'Ascending'
        },
        
        manipulatedUser : {
            username : 'some username',
            password : '',
            name : 'some name',
            surname : 'some surname',
            dateOfBirth : '1999-07-04',
            gender : 'MALE',
            role : 'ADMIN',
            status : 'normal',
            cameFrom : 'editUser'
        }
    },
    created (){
    },
    async mounted (){
        let user = window.localStorage.getItem('User');
        this.currentUser = JSON.parse(user);
        this.currentUser = {};
        this.currentUser.username = 'curr';

        this.changeDisplayedUsers('shoppers');

        this.selectedRestaurant = this.restaurants[0];
    },
    methods : {
        changeFilterMark(value, dropdown) {
            this.restaurantFilterObj.mark = value;
            
            openDropDown(dropdown);
        },
        changeAscDes() {
            if (this.restaurantFilterObj.ascDes.toLowerCase().includes('ascending')){
                this.restaurantFilterObj.ascDes = 'Descending';
                $("button[name='ascDescButton']").get(0).style.setProperty('background-image', 'url("../Images/angleDown.png")');
            }
            else {
                this.restaurantFilterObj.ascDes = 'Ascending';
                $("button[name='ascDescButton']").get(0).style.setProperty('background-image', 'url("../Images/angleUp.png")');
            }
        },
        changeAscDesUser() {
            if (this.userVisible == 'shoppers'){
                if (this.shopperFilterObj.ascDes.toLowerCase().includes('ascending')){
                    this.shopperFilterObj.ascDes = 'Descending';
                    $("button[name='ascDescShopperButton']").get(0).style.setProperty('background-image', 'url("../Images/angleDown.png")');
                }
                else {
                    this.shopperFilterObj.ascDes = 'Ascending';
                    $("button[name='ascDescShopperButton']").get(0).style.setProperty('background-image', 'url("../Images/angleUp.png")');
                }
            }
            else if (this.userVisible == 'managers'){
                if (this.managerFilterObj.ascDes.toLowerCase().includes('ascending')){
                    this.managerFilterObj.ascDes = 'Descending';
                    $("button[name='ascDescManagerButton']").get(0).style.setProperty('background-image', 'url("../Images/angleDown.png")');
                }
                else {
                    this.managerFilterObj.ascDes = 'Ascending';
                    $("button[name='ascDescManagerButton']").get(0).style.setProperty('background-image', 'url("../Images/angleUp.png")');
                }
            }
            else if (this.userVisible == 'deliveryWorkers'){
                if (this.deliveryWorkerFilterObj.ascDes.toLowerCase().includes('ascending')){
                    this.deliveryWorkerFilterObj.ascDes = 'Descending';
                    $("button[name='ascDescDeliveryButton']").get(0).style.setProperty('background-image', 'url("../Images/angleDown.png")');
                }
                else {
                    this.deliveryWorkerFilterObj.ascDes = 'Ascending';
                    $("button[name='ascDescDeliveryButton']").get(0).style.setProperty('background-image', 'url("../Images/angleUp.png")');
                }
            }
            else if (this.userVisible == 'admins'){
                if (this.adminFilterObj.ascDes.toLowerCase().includes('ascending')){
                    this.adminFilterObj.ascDes = 'Descending';
                    $("button[name='ascDescAdminButton']").get(0).style.setProperty('background-image', 'url("../Images/angleDown.png")');
                }
                else {
                    this.adminFilterObj.ascDes = 'Ascending';
                    $("button[name='ascDescAdminButton']").get(0).style.setProperty('background-image', 'url("../Images/angleUp.png")');
                }
            }
        },
        changeDisplay(view){
            this.visible = view;
        },
        displaySpecificRestaurant(restaurant){
            this.selectedRestaurant = restaurant;
            this.visible = 'specificRestaurant';
        },
        chooseManager(value, dropdown){
            this.tempRestaurant.managerId = value.id;

            openDropDown(dropdown);
        },
        chooseType(value, dropdown){
            this.tempRestaurant.type = value;

            openDropDown(dropdown);
        },
        editRestaurantViewChange(){
            this.tempRestaurant = Object.assign({}, this.selectedRestaurant);

            this.visible = 'addEditRestaurant';
        },
        addRestaurantViewChange(){
            this.tempRestaurant = {
                name : '',
                type : '',
                location : '',
                geoLocation : '',
                logo : '',
                managerId : ''
            }
            
            this.visible = 'addEditRestaurant';
        },
        getManagerByNameSurnameById(id){
            for(manager of this.allManagers){
                if (id == manager.id){
                    return manager.name + ' ' + manager.surname;
                }
            }
        },
        changeDisplayedUsers(users){
            this.userVisible = users;

            changeTag(users);
        },
        changeFilterShopperType(value, dropdown){
            this.shopperFilterObj.shopperType = value;

            openDropDown(dropdown);
        },
        chooseGender(value, dropdown){
            this.manipulatedUser.gender = value;

            openDropDown(dropdown);
        },
        displayRole(){
            return this.manipulatedUser.role.replace('_',' ');
        },
        chooseRole(value, dropdown){
            this.manipulatedUser.role = value;

            openDropDown(dropdown);
        },
        
        editShopper(shopper){
            this.manipulatedUser.username = shopper.username;
            this.manipulatedUser.name = shopper.name;
            this.manipulatedUser.surname = shopper.surname;
            this.manipulatedUser.dateOfBirth = shopper.dateOfBirth;
            this.manipulatedUser.gender = shopper.gender;
            this.manipulatedUser.status = shopper.status;
            this.manipulatedUser.role = "SHOPPER";
            this.manipulatedUser.cameFrom = 'editUser';

            this.visible = 'addEditUser';
        },
        editManager(manager){
            this.manipulatedUser.username = manager.username;
            this.manipulatedUser.name = manager.name;
            this.manipulatedUser.surname = manager.surname;
            this.manipulatedUser.dateOfBirth = manager.dateOfBirth;
            this.manipulatedUser.gender = manager.gender;
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.role = "MANAGER";
            this.manipulatedUser.cameFrom = 'editUser';

            this.visible = 'addEditUser';
        },
        editDeliveryWorker(delWorker){
            this.manipulatedUser.username = delWorker.username;
            this.manipulatedUser.name = delWorker.name;
            this.manipulatedUser.surname = delWorker.surname;
            this.manipulatedUser.dateOfBirth = delWorker.dateOfBirth;
            this.manipulatedUser.gender = delWorker.gender;
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.role = "DELIVERY_WORKER";
            this.manipulatedUser.cameFrom = 'editUser';

            this.visible = 'addEditUser';
        },
        addNewUser(){
            this.manipulatedUser.username = '';
            this.manipulatedUser.name = '';
            this.manipulatedUser.surname = '';
            this.manipulatedUser.dateOfBirth = '';
            this.manipulatedUser.gender = '';
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.role = '';
            this.manipulatedUser.cameFrom = 'addUser';

            this.visible = 'addEditUser';
        },
        editMyAccount() {
            this.manipulatedUser.username = this.currentUser.username;
            this.manipulatedUser.name = this.currentUser.username;
            this.manipulatedUser.surname = this.currentUser.username;
            this.manipulatedUser.dateOfBirth = this.currentUser.username;
            this.manipulatedUser.gender = this.currentUser.username;
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.role = 'ADMIN';
            this.manipulatedUser.cameFrom = 'editUser';

            this.visible = 'addEditUser';
        }
    },
    computed: {
    },
    watch: {
    }
})