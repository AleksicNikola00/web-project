//visible : restaurants, viewCart, myProfile, orders, specificRestaurant, comment

var webShop = new Vue({
    el: '#dashboard',
    data: {
        restaurants : [],
        receivedRestaurants : [
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                open : 'true'
            },
            {
                name : 'Neki',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                open : 'false'
            },
            {
                name : 'Drugi',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                open : 'false'
            },
            {
                name : 'Opet',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                open : 'true'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                open : 'true'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                open : 'false'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                open : 'true'
            }
        ],
        selectedButton : {},
        currentUser : {
            username : 'mili',
            firstname : 'Nikola',
            lastname : 'Milosavljevic',
            email : 'nikolamilosa20@gmail.com',
            dateOfBirth: '4-7-1999',
            points : '1000'
        },
        tempCurrUser : {},
        visible : 'restaurants',
        status : {},
        filterObj : {
            name : '',
            location : '',
            type : '',
            rating : 1,
            isOpen : false,
            isAsc : true,
            isDesc : false
        }
    },
    created (){
    },
    mounted (){
        this.restaurants = this.receivedRestaurants.filter(rest => rest.name.includes(''));
        this.selectSubmenu(this.visible);

        this.tempCurrUser = Object.assign({}, this.currentUser);
        this.tempCurrUser.dateOfBirth = this.convertDate(this.tempCurrUser.dateOfBirth);
        this.tempCurrUser.points = parseInt(this.tempCurrUser.points);

        if (this.tempCurrUser.points < 500)
            this.status = 'bronze';
        else if (this.tempCurrUser.points < 1000)
            this.status = 'silver';
        else
            this.status = 'gold';
    },
    methods : {
        selectSubmenu : function(submenu){
            actualSubmenu = submenu;
            if (submenu == 'specificRestaurant')
                actualSubmenu = 'restaurants';
            else if (submenu == 'comment')
                actualSubmenu = 'orders';

            if (Object.keys(this.selectedButton).length != 0){
                this.selectedButton.removeClass('btn-primary');
                this.selectedButton.addClass('btn-light');
            }
            
            this.selectedButton = $('#' + actualSubmenu);
            this.selectedButton.removeClass('btn-light');
            this.selectedButton.addClass('btn-primary');
            this.visible = submenu;
            return;
        },
        convertDate : function(date) {
            parts = date.split('-');

            day = parseInt(parts[0]);
            
            if (day < 10){
                day = "0" + day.toString();
            }
            else{
                day = day.toString();
            }

            month = parseInt(parts[1]);
            
            if (month < 10){
                month = "0" + month.toString();
            }
            else{
                month = month.toString();
            }

            d = parts[2] + "-" + month + "-" + day;

            return d;
        },
        filterName : function() {
            this.restaurants = this.receivedRestaurants.filter(rest => rest.name.toLowerCase().includes(this.filterObj.name.toLowerCase()));
            return;
        }
    },
    computed: {
        filterObjName() {
            return this.filterObj.name;
        }
    },
    watch: {
        filterObjName() {
            this.filterName();
        }
    }
})