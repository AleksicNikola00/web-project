//visible : restaurants, viewCart, myProfile, orders, specificRestaurant, comment

var webShop = new Vue({
    el: '#dashboard',
    data: {
        restaurants : [],
        receivedRestaurants : [
            {
                name : 'Pizza',
                type : 'Italian',
                rating : '2',
                address : 'Test a',
                city : 'Novi Sad',
                open : 'true'
            },
            {
                name : 'Neki',
                type : 'Turkish',
                rating : '3',
                address : 'Test b',
                city : 'Novi Sad',
                open : 'false'
            },
            {
                name : 'Naki',
                type : 'Turkish',
                rating : '3',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                open : 'false'
            },
            {
                name : 'Opet',
                type : 'Cheenese',
                rating : '2',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                open : 'true'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '5',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                open : 'true'
            },
            {
                name : 'Ciao',
                type : 'Itelian',
                rating : '1',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                open : 'false'
            },
            {
                name : 'Ciao',
                type : 'Cheenese',
                rating : '4',
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
            points : '600'
        },
        tempCurrUser : {},
        visible : 'restaurants',
        status : {},
        filterObj : {
            name : '',
            location : '',
            type : '',
            rating : 'all',
            isOpen : false,
            isAsc : true,
            isDesc : false
        },
        selectedRestestaurant : {}
    },
    created (){
    },
    mounted (){
        this.restaurants = this.receivedRestaurants.filter(rest => rest.name.includes(''));
        this.selectSubmenu(this.visible);

        this.tempCurrUser = Object.assign({}, this.currentUser);
        this.tempCurrUser.dateOfBirth = this.convertDate(this.tempCurrUser.dateOfBirth);
        this.tempCurrUser.points = parseInt(this.tempCurrUser.points);
        this.selectStatus();
        
    },
    methods : {
        selectStatus : function() {
            if (this.tempCurrUser.points < 500)
            this.status = 'bronze';
            else if (this.tempCurrUser.points < 1000)
                this.status = 'silver';
            else
                this.status = 'gold';
        },
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
        descClick : function(){
            this.filterObj.isAsc = false;
            this.filterObj.isDesc = true;
        },
        ascClick : function(){
            this.filterObj.isDesc = false;
            this.filterObj.isAsc = true;
        },
        isOpenClick : function(){
            this.filterObj.isOpen = !this.filterObj.isOpen;
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
        filterName : function(restaurants) {
            let result;
            result = restaurants.filter(rest => rest.name.toLowerCase().includes(this.filterObj.name.toLowerCase()));
            result.sort(function(a, b){
                return ('' + a.name).localeCompare(b.name);
            });

            if (this.filterObj.isDesc)
            {
                result.reverse();
            }
            return result;
        },
        filterType : function(restaurants) {
            let result;
            result = restaurants.filter(rest => rest.type.toLowerCase().includes(this.filterObj.type.toLowerCase()));
            result.sort(function(a, b){
                return ('' + a.type).localeCompare(b.type);
            });

            if (this.filterObj.isDesc)
            {
                result.reverse();
            }
            return result;
        },
        filterLocation : function(restaurants){
            let result;
            result = restaurants.filter(rest => rest.address.toLowerCase().includes(this.filterObj.location.toLowerCase()));
            result.sort(function(a, b){
                return ('' + a.location).localeCompare(b.location);
            });

            if (this.filterObj.isDesc){
                result.reverse();
            }

            return result;
        },
        filterRating : function(restaurants){
            let result;
            result = restaurants.filter(rest => parseFloat(rest.rating) >= parseFloat(this.filterObj.rating));
            result.sort(function(a, b){
                return a.rating - b.rating;
            });

            if (this.filterObj.isDesc){
                result.reverse();
            }

            return result;
        },
        filterIsOpen : function(restaurants){
            let result;
            result = restaurants.filter(rest => rest.open == 'true');

            return result;
        },
        doFilter : function(){
            let result = this.receivedRestaurants;
            
            
            if (this.filterObj.rating != 'all'){
                result = this.filterRating(result);
            }
            if (this.filterObj.isOpen){
                result = this.filterIsOpen(result);
            }
            if (this.filterObj.type != ''){
                result = this.filterType(result);
            }
            if (this.filterObj.location != ''){
                result = this.filterLocation(result);
            }
            if (this.filterObj.name != ''){
                result = this.filterName(result);
            }

            this.restaurants = result;
        },

        pressedRestaurant : function(restaurant){
            
        }
    },
    computed: {
        /* used for filtering */
        filterObjName() {
            return this.filterObj.name;
        },
        filterObjType() {
            return this.filterObj.type;
        },
        filterObjLocation() {
            return this.filterObj.location;
        },
        filterObjRaiting() {
            return this.filterObj.rating;
        },
        filterObjIsOpen() {
            return this.filterObj.isOpen;
        }
    },
    watch: {
        /* used for filtering */
        filterObjName() {
            this.doFilter();
        },
        filterObjType() {
            this.doFilter();
        },
        filterObjLocation() {
            this.doFilter();
        },
        filterObjRaiting() {
            this.doFilter();
        },
        filterObjIsOpen() {
            this.doFilter();
        }
    }
})