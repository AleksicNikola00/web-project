//visible : restaurants, specificRestaurant, addEditRestaurant, users, specificUser, addEditUser
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
        visible : 'users',
        userVisible : 'shoppers',
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

        shopperFilterObj : {
            name : '',
            surname : '',
            username : '',
            ascDes : 'Ascending',
            points : 0,
            shopperType : 'BRONZE',
        }
    },
    created (){
    },
    async mounted (){
        let user = window.localStorage.getItem('User');
        this.currentUser = JSON.parse(user);

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
                $("button[name='ascDescButton']").css('background-image', 'url("../Images/angleDown.png")');
            }
            else {
                this.restaurantFilterObj.ascDes = 'Ascending';
                $("button[name='ascDescButton']").css('background-image', 'url("../Images/angleUp.png")');
            }
        },
        changeAscDesUser() {
            if (this.userVisible == 'shoppers'){
                if (this.shopperFilterObj.ascDes.toLowerCase().includes('ascending')){
                    this.shopperFilterObj.ascDes = 'Descending';
                    $("button[name='ascDescShopperButton']").css('background-image', 'url("../Images/angleDown.png")');
                }
                else {
                    this.shopperFilterObj.ascDes = 'Ascending';
                    $("button[name='ascDescShopperButton']").css('background-image', 'url("../Images/angleUp.png")');
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
        }
    },
    computed: {
    },
    watch: {
    }
})