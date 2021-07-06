var app = new Vue({
	el: '#dashboard',
	data: {
        selectedButton : '',
        orderSorter: '',
        orderFilter: {
            name: '',
            type: '',
            status: '',
            dateFrom: undefined,
            dateTo: undefined,
            priceFrom: undefined,
            priceTo: undefined,
            isAsc: true
        },
        allOrders: [
            {
                restaurantName : 'Ciao',
                img : '../Images/gold-member.png',
                items : [
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    },
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '1500'
                    }
                ],
                status : 'Delievered',
                date : '2:23 6-6-2021',
                restaurantType : 'Italian',
                price : 2000
            },
            {
                restaurantName : 'Neki',
                img : '../Images/silver-member.png',
                items : [
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    },
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    },
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    },
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    }
                ],
                status : 'Waiting delivery',
                date : '23:23 6-16-2021',
                restaurantType : 'Italian',
                price: 1000
            },
            {
                restaurantName : 'Naki',
                img : '../Images/bronze-member.png',
                items : [
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    },
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    }
                ],
                status : 'In transport',
                date : '12:12 5-4-2021',
                restaurantType : 'Greek',
                price: 1500
            }
        ],
        orders :[
            {
                restaurantName : 'Ciao',
                img : '../Images/gold-member.png',
                items : [
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    },
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '1500'
                    }
                ],
                status : 'Delievered',
                date : '2:23 6-6-2021',
                restaurantType : 'Italian',
                price : 2000
            },
            {
                restaurantName : 'Neki',
                img : '../Images/silver-member.png',
                items : [
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    },
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    },
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    },
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    }
                ],
                status : 'Waiting delivery',
                date : '23:23 6-16-2021',
                restaurantType : 'Italian',
                price: 1000
            },
            {
                restaurantName : 'Naki',
                img : '../Images/bronze-member.png',
                items : [
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    },
                    {
                        name : 'pizza',
                        amount : '2',
                        price : '500'
                    }
                ],
                status : 'In transport',
                date : '12:12 5-4-2021',
                restaurantType : 'Greek',
                price: 1500
            }
        ],
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
            gender: undefined,
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
        this.setCurrentUser();
	},

	methods: {

        setCurrentUser: function(){
            let user = JSON.parse(window.localStorage.getItem('User'));
            this.user.username = user.username;
            this.user.name = user.firstname;
            this.user.surname = user.lastname;
            this.user.gender = user.gender;
            this.user.date = this.convertDate(user.dateOfBirth);
            this.user.password = user.password;
        },

        convertDate : function(date) {
            parts = date.split('-');
            day = parseInt(parts[0]);
            if (day < 10)
                day = "0" + day.toString();
            else
                day = day.toString();
            month = parseInt(parts[1]);
            if (month < 10)
                month = "0" + month.toString();
            else
                month = month.toString();
            d = parts[2] + "-" + month + "-" + day;
            return d;
        },
        
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
                this.updateUser();
                $('#toastPWSuccess').toast('show');
        },

        updateUser:async function(){
            let userToUpdate = {
				username : this.user.username,
				name : this.user.name,
				surname : this.user.surname,
				gender : this.user.gender,
				dateOfBirth : new Date(),
				password : this.user.new_password
			}

            let year = parseInt(this.user.date.split("-")[0]);
			let day = parseInt(this.user.date.split("-")[2]);
			let month = parseInt(this.user.date.split("-")[1]) - 1;
			
			userToUpdate.dateOfBirth = new Date(year,month,day);
            //
            await axios.put('/WebShop/rest/user/updateworker',userToUpdate)
					.then(response => {
						let user = response.data;
                        window.localStorage.setItem('User',JSON.stringify(user));
                        this.setCurrentUser();
					});
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

        deliveredOrder: function(order){
            order.status = 'Delievered';//toastDeliverSuccess
            $('#toastDeliverSuccess').toast('show');
        },
        requestOrder: function(order){
            order.status='Pending request';//toastDeliveryRequested
            $('#toastDeliveryRequested').toast('show');
        },

        filterOrders: function(){
            this.filterOrderByName();
            this.filterOrderByType();
            this.filterOrderByDate();
            this.filterOrderByStatus();
            this.filterOrderByPrice();
            this.sortOrders();
        },

        filterOrderByName: function(){
            this.orders = this.allOrders.filter(r => r.restaurantName.toLowerCase().includes(this.orderFilter.name.toLowerCase()));
        },

        filterOrderByType: function(){
            this.orders = this.orders.filter(r => r.restaurantType.toLowerCase().includes(this.orderFilter.type.toLowerCase()));
        },
        
        filterOrderByDate: function(){
            if(!(this.orderFilter.dateFrom === undefined))
                this.orders = this.orders.filter(order => this.calculateDate(order.date) >= Date.parse(this.orderFilter.dateFrom));
            if(!(this.orderFilter.dateTo === undefined))
                this.orders = this.orders.filter(order => this.calculateDate(order.date) <= Date.parse(this.orderFilter.dateTo));
      
        },

        filterOrderByStatus: function(){
            if(this.orderFilter.status.length >0)
                this.orders = this.orders.filter(order => this.orderFilter.status == order.status);
        },

        filterOrderByPrice: function(){
            if(!(this.orderFilter.priceFrom === undefined))
                this.orders = this.orders.filter(order => order.price >= this.orderFilter.priceFrom);
            if(!(this.orderFilter.priceTo === undefined))
                this.orders = this.orders.filter(order => order.price <= this.orderFilter.priceTo);
        },

        sortOrders: function(){
            if(this.orderSorter == 'name')
                this.sortOrdersByName();
            else if(this.orderSorter == 'price')
                this.sortOrdersByPrice();
            else if(this.orderSorter == 'date')
                this.sortOrdersByDate();
            
            if(!this.orderFilter.isAsc)
                this.orders.reverse();
        },

        setOrderSorter: function(preference){
            this.orderSorter = preference;
            this.sortOrders();
        },

        sortOrdersByName: function(){
            this.orders.sort((a,b) => a.restaurantName.localeCompare(b.restaurantName));
        },

        
        sortOrdersByPrice: function(){
            this.orders.sort((a,b) => b.price - a.price);
        },

        
        sortOrdersByDate: function(){
            this.orders.sort(function(a,b){
                return new Date(b.date) - new Date(a.date);
              });
        },

        calculateDate : function(date) {
            let result = new Date();
            
            let specificTime = date.split(' ')[0];
            let specificDate = date.split(' ')[1];

            result.setHours(parseInt(specificTime.split(':')[0]), parseInt(specificTime.split(':')[1]));

            result.setFullYear(parseInt(specificDate.split('-')[2]), parseInt(specificDate.split('-')[0]) - 1, parseInt(specificDate.split('-')[1]));

            return result;
        },

        logOut: function(){
            alert("Log Out!");
        }

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

        'orderFilter.name':function(){
            this.filterOrders();
        },
        'orderFilter.type':function(){
            this.filterOrders();
        },
        'orderFilter.dateFrom':function(){
            this.filterOrders();
        },
        'orderFilter.dateTo':function(){
            this.filterOrders();
        },
        'orderFilter.status':function(){
            this.filterOrders();
        },
        'orderFilter.priceFrom':function(){
            this.filterOrders();
        },
        'orderFilter.priceTo':function(){
            this.filterOrders();
        },
        'orderFilter.isAsc':function(){
            this.orders.reverse();
        },
        
    }

});