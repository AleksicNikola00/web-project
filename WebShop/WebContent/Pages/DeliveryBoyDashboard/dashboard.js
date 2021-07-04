var app = new Vue({
	el: '#dashboard',
	data: {
        selectedButton : '',
        activeSubmenu : '',
        restaurantSorter: '',
        restaurantComments: [
            {
                username: 'panda',
                comment: 'Hrana je krimi-rad necu vise nikad jesti odje!',
                mark: 1
            },
            {
                username: 'Jole',
                comment: 'Hrana je Topcina od sad cu uvek ovde da jedem!',
                mark: 3
            },
            {
                username: 'panda',
                comment: 'dasdsadasdadasdadsadasdasdasdasdasdnasjdnasodmasspdnmasindas-md[loasmdiasnd0iansm[odaslospdmasidnaspdmap;lknmdain!',
                mark: 4
            },
            {
                username: 'losmi',
                comment: 'Hrana je fuj, necu vise nikad jesti odje!',
                mark: 1
            },
        ],
        restaurantItems: [
         {
            img: '../Images/bronze-member.png',
            name: 'Coca-cola',
            type: 'drink',
            price: 23.5
        },
        {
            img: '../Images/gold-member.png',
            name: 'Coca-Fanta',
            type: 'food',
            price: 20.5
        },
        {
            img: '../Images/silver-member.png',
            name: 'Sprite',
            type: 'food',
            price: 200.5
        },
    ],
        selectedRestaurant: {
                name : '',
                type : '',
                rating : '',
                address : '',
                city : '',
                img : '',
                location : '',
                open : true
        },
        user : {
            username: 'Koljisivoje',
            name: 'Njikalaj',
            surname: 'Aljeksijevic',
            gender: 'FEMALE',
            date: '2000-02-22',
            password : '12345678',
            new_password: ''
        },
        filterRestaurant : {
            name : '',
            location : '',
            type : '',
            rating : 0,
            isOpen : false,
            isAsc : true
        },
        restaurants : [
            {
                name : 'Pizza',
                type : 'Italian',
                rating : '2',
                address : 'Test a',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad Test a',
                open : true
            },
            {
                name : 'Neki',
                type : 'Turkish',
                rating : '3',
                address : 'Test b',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad Test b',
                open : false
            },
            {
                name : 'Naki',
                type : 'Turkish',
                rating : '3',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad  Bulevar oslobodjenja',
                open : false
            },
            {
                name : 'Opet',
                type : 'Cheenese',
                rating : '2',
                address : 'Bulevar oslobodjenja 2',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 2',
                open : true
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '5',
                address : 'Bulevar oslobodjenja 3',
                city : 'Novi Sad',
                img : '../Images/gold-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 3',
                open : true
            },
            {
                name : 'Ciao',
                type : 'Itelian',
                rating : '1',
                address : 'Bulevar oslobodjenja 5',
                city : 'Novi Sad',
                img : '../Images/silver-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 5',
                open : false
            },
            {
                name : 'Ciao',
                type : 'Cheenese',
                rating : '4',
                address : 'Bulevar oslobodjenja 22',
                city : 'Novi Sad',
                img : '../Images/silver-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 22',
                open : true
            }
        ],
        allRestaurants :[
            {
                name : 'Pizza',
                type : 'Italian',
                rating : '2',
                address : 'Test a',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad Test a',
                open : true
            },
            {
                name : 'Neki',
                type : 'Turkish',
                rating : '3',
                address : 'Test b',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad Test b',
                open : false
            },
            {
                name : 'Naki',
                type : 'Turkish',
                rating : '3',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad  Bulevar oslobodjenja',
                open : false
            },
            {
                name : 'Opet',
                type : 'Cheenese',
                rating : '2',
                address : 'Bulevar oslobodjenja 2',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 2',
                open : true
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '5',
                address : 'Bulevar oslobodjenja 3',
                city : 'Novi Sad',
                img : '../Images/gold-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 3',
                open : true
            },
            {
                name : 'Ciao',
                type : 'Itelian',
                rating : '1',
                address : 'Bulevar oslobodjenja 5',
                city : 'Novi Sad',
                img : '../Images/silver-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 5',
                open : false
            },
            {
                name : 'Ciao',
                type : 'Cheenese',
                rating : '4',
                address : 'Bulevar oslobodjenja 22',
                city : 'Novi Sad',
                img : '../Images/silver-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 22',
                open : true
            }
        ],
        
	},

	mounted() {
		this.selectedButton = 'restaurants';
        this.changeVisibility();
        this.sortByStatus();
	},

	methods: {
		selectSubmenu: function(submenu){
			var elem = $('#' + this.selectedButton);
            elem.removeClass('btn-primary');
            elem.addClass('btn-light');
            //na novog
            this.selectedButton = submenu
            var elem = $('#' + this.selectedButton);
            elem.removeClass('btn-light');
            elem.addClass('btn-primary');
            this.changeVisibility();
		},

        changeUser: function(){
            if(this.user.new_password.length > 0)
            {
                if(this.user.new_password.length < 8)
                    {$('#toastPWError1').toast('show'); return;}
                if(document.getElementById("oldpassword").value.localeCompare(this.user.password) != 0)
                    {$('#toastPWError2').toast('show'); return;}
                this.user.password = document.getElementById("oldpassword").value;
            }
                $('#toastPWSuccess').toast('show');
        },

        changeVisibility: function(){
            this.activeSubmenu = this.selectedButton;
        },

        detailsClicked: function(selectedRestaurant){
            this.selectedRestaurant = selectedRestaurant;
            this.activeSubmenu = 'restaurantDetails';
        },

        updateTextInput: function(){
            document.getElementById('textInput').value=document.getElementById('mark').value; 
            this.filterRestaurant.rating = document.getElementById('mark').value;
        },

        filterRestaurants: function(){
            this.filterByName();
            this.filterByLocation();
            this.filterByType();
            this.filterByRating();
            this.filterByStatus();
            this.sortRestaurants();
        },

        sortRestaurants: function(){
            if(this.restaurantSorter == 'name')
                this.sortByName();
            else if(this.restaurantSorter == 'location')
                this.sortByLocation();
            else if(this.restaurantSorter == 'mark')
                this.sortByMark();

            if(!this.filterRestaurant.isAsc)//ako vec stoji na desc da ga obrne
                this.restaurants.reverse();
        },

        setSorter: function(preference){
            this.restaurantSorter = preference;
        },

        sortByName: function(){
            this.restaurants.sort((a,b) => a.name.localeCompare(b.name));
        },

        sortByLocation: function(){
            this.restaurants.sort((a,b) => a.location.localeCompare(b.location));
        },

        sortByMark: function(){
            this.restaurants.sort((a,b) => b.rating - a.rating);
        },

        sortByStatus: function(){
            for(i = 0;i<3;i++)//pojma nemam zasto al kad tri put izvrti onda ga sortira kako treba
                this.restaurants.sort((x, y) => x.open == y.open? 0 : y? -1 : 1);   
        },




        filterByName: function(){
            this.restaurants = this.allRestaurants.filter(r => r.name.toLowerCase().includes(this.filterRestaurant.name.toLowerCase()));
        },

        filterByLocation: function(){
            this.restaurants = this.restaurants.filter(r => r.location.toLowerCase().includes(this.filterRestaurant.location.toLowerCase()));
        },

        filterByType: function(){
            this.restaurants = this.restaurants.filter(r => r.type.toLowerCase().includes(this.filterRestaurant.type.toLowerCase()));
        },

        filterByRating: function(){
            this.restaurants = this.restaurants.filter(r => r.rating >= this.filterRestaurant.rating);
        },
        
        filterByStatus: function(){//open or closed
            if(this.filterRestaurant.isOpen)
                this.restaurants = this.restaurants.filter(r => r.open === this.filterRestaurant.isOpen);
        },



	},

    computed:{

    },

    watch:{
        'filterRestaurant.name': function(){
            this.filterRestaurants();
        },
        'filterRestaurant.location': function(){
            this.filterRestaurants();
        },
        'filterRestaurant.type': function(){
            this.filterRestaurants();
        },
        'filterRestaurant.rating': function(){
            this.filterRestaurants();
        },
        'filterRestaurant.isOpen': function(){
            this.filterRestaurants();
        },
        'filterRestaurant.isAsc': function(){
            this.restaurants.reverse();
        },
        'restaurantSorter':function(){
            this.sortRestaurants();
        },

        
    }

});