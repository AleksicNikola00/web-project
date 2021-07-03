//visible : restaurants, specificRestaurant, addEditRestaurant, users, specificUser, addEditUser

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

var webShop = new Vue({
    el: '#dashboard',
    data: {
        visible : 'specificRestaurant',
        currentUser : {},
        receivedRestaurants : [],
        receivedUsers : [],
        users : [],
        restaurants : [
            {
                name : 'First',
                type : 'Turkish',
                mark : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24'
            },
            {
                name : 'First',
                type : 'Turkish',
                mark : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24'
            },
            {
                name : 'First',
                type : 'Turkish',
                mark : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24'
            },
            {
                name : 'First',
                type : 'Turkish',
                mark : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24'
            },
            {
                name : 'First',
                type : 'Turkish',
                mark : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24'
            },
            {
                name : 'First',
                type : 'Turkish',
                mark : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24'
            },
            {
                name : 'First',
                type : 'Turkish',
                mark : '5',
                manager : 'Dzoni bova',
                location : 'Stevana Mokranjca 24'
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
            }
        ],
    },
    created (){
    },
    async mounted (){
        let user = window.localStorage.getItem('User');
        this.currentUser = JSON.parse(user);

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
        changeDisplay(view){
            this.visible = view;
        },
        displaySpecificRestaurant(restaurant){
            this.selectedRestaurant = restaurant;
            this.visible = 'specificRestaurant';
        }
    },
    computed: {
    },
    watch: {
    }
})