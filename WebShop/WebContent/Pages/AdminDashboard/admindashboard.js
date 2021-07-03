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
        visible : 'restaurants',
        currentUser : {},
        receivedRestaurants : [],
        receivedUsers : [],
        users : [],
        restaurants : [],
        restaurantFilterObj : {
            name : '',
            type : '',
            location : '',
            mark : 'All marks',
            ascDes : 'Ascending'
        }
    },
    created (){
    },
    async mounted (){
        let user = window.localStorage.getItem('User');
        this.currentUser = JSON.parse(user);
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
        }
    },
    computed: {
    },
    watch: {
    }
})