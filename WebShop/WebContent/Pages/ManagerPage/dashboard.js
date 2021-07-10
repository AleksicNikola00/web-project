var app = new Vue({
	el: '#dashboard',
	data: {
        selectedButton : '',
        myRestaurantSubMenu: '',
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
        allOrders: [],
        orders :[{
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
            status : 'PENDING DELIVERY',
            date : '2:23 6-6-2021',
            restaurantType : 'Italian',
            price : 2000
        },
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
            status : 'PENDING',
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
            status : 'IN PREPARATION',
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
            status : 'IN TRANSPORT',
            date : '12:12 5-4-2021',
            restaurantType : 'Greek',
            price: 1500
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
            status : 'WAITING DELIVERY',
            date : '12:12 5-4-2021',
            restaurantType : 'Greek',
            price: 1500
        }
    ],
        activeSubmenu : '',
        restaurantSorter: '',
        restaurantComments: [],
        selectedItem: {
               id: null,
               img: '',
               name: '',
               type: '',
               price: 0,
               unitAmount: 0,
               description: ""
        },
        restaurantItems: [],
        myRestaurant:{},
        myRestaurantItems: [],
        myRestaurantComments: [],
        selectedRestaurant: {
            name : 'Pizza',
            type : 'Italian',
            rating : '2',
            address : 'Test a',
            city : 'Novi Sad',
            img : '../Images/bronze-member.png',
            location : 'Novi Sad Test a',
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
        restaurants : [],
        allRestaurants :[],
        shoppers: [{
            firstname: "Kolja",
            lastname: "Koljic",
            username: "Trala",
            dateOfBirth: "22-02-2000",
            gender: "MALE",
            type: "BRONZE"
        },{
            firstname: "Trass",
            lastname: "Koljic",
            username: "Trala",
            dateOfBirth: "22-02-2000",
            gender: "MALE",
            type: "BRONZE"
        },{
            firstname: "Trass",
            lastname: "Koljic",
            username: "dasdad",
            dateOfBirth: "22-02-1980",
            gender: "MALE",
            type: "BRONZE"
        },{
            firstname: "Kolja",
            lastname: "Koljic",
            username: "asdasd",
            dateOfBirth: "22-01-2000",
            gender: "MALE",
            type: "BRONZE"
        },]
        
	},

	async mounted() {
        // if (window.localStorage.getItem("User") === null) {
        //     window.location.replace("http://localhost:8080/WebShop/");
        //     return;
        // }

		this.selectedButton = 'restaurants';
        

        this.setCurrentUser();
        await this.requestRestaurants();
        await this.requestOrders();
        await this.requestMyRestaurant();
        
        this.changeVisibility();
	},

	methods: {
        requestMyRestaurant: async function(){
            return await axios.get('/WebShop/rest/restaurant/'+ this.user.username)
                        .then(response =>{
                            this.myRestaurant = response.data;
                            this.myRestaurant.rating = parseFloat(this.myRestaurant.rating).toFixed(2);
                            this.myRestaurant.location = this.myRestaurant.city+ " "+this.myRestaurant.address;
                            this.setMyRestaurant();
                        });
        },

        setMyRestaurant: async function(){ 
            await this.loadMyRestaurantComments();
            await this.loadMyItems();
        },

        loadMyRestaurantComments: async function(){
            return await axios.get('/WebShop/rest/getcomments/all/' +this.myRestaurant.id)
						.then(response => {
							this.myRestaurantComments = response.data;
						});
        },

        loadMyItems: async function(selectedRestaurant){
            await axios.get('/WebShop/rest/getitemsforrestaurant/' + this.myRestaurant.id)
						.then(response => {
							this.myRestaurantItems = response.data;
						});
        },

        updateItem: function(){
            if( this.selectedItem.id == null)
            {
                    alert("novi item");
            }
            else
            {
                    alert("EDITOVAN");
            }
            this.myRestaurantSubMenu = 'myRestaurant'
        },

        requestOrders: async function(){
            return await axios.get('/WebShop/rest/getorders/worker/' + this.user.username)
						.then(response => {
							this.allOrders = response.data;
							for (order of this.allOrders){
								order.status = order.status.replace("_"," ");
							}
							
							this.allOrders.reverse();
                            this.orders = Object.assign({}, this.allOrders);
						});
        },

        changeRestaurantSubmenu: function(submenu){
            this.myRestaurantSubMenu = submenu;
        },

        async requestRestaurants(){
			
			return await axios.get('/WebShop/rest/getrestaurants')
						.then(response => {
							this.allRestaurants = new Array();
                            this.restaurants = new Array();
                            let vm = this;
                            response.data.forEach(restaurant =>{
                                restaurant.location = restaurant.address + " " + restaurant.city;
                                restaurant.rating = parseFloat(restaurant.rating).toFixed(2);
                                vm.allRestaurants.push(restaurant);
                                vm.restaurants.push(restaurant)
                            });
                            this.sortByStatus();
						});
			
        },

        editItem: function(item){
          this.myRestaurantSubMenu = 'edit';
          this.selectedItem = item;  
        },

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
            this.selectedButton = submenu;
            this.myRestaurantSubMenu = submenu;
            var elem = $('#' + this.selectedButton);
            elem.removeClass('btn-light');
            elem.addClass('btn-primary');
            this.changeVisibility();
		},

        allowComment: async function(comment){
            comment.status = 'ALLOWED';
            await axios.put('/WebShop/rest/comment/update',comment)
					.then(response => {
						console.log(response.data);
                        this.requestMyRestaurant();
					});

            alert("Comment allowed!");
        },

        rejectComment:async function(comment){
            comment.status = 'REJECTED';
            await axios.put('/WebShop/rest/comment/update',comment)
					.then(response => {
						console.log(response.data);
					});
            alert("Comment rejected!");
        },

        changeUser: function(){
            if(this.user.new_password.length > 0)
            {
                if(this.user.new_password.length < 8)
                    {alert("Password must be at least 8 characters long!"); return;}
                if(document.getElementById("oldpassword").value.localeCompare(this.user.password) != 0)
                    {alert("Password missmatch!"); return;}
                this.user.password = document.getElementById("oldpassword").value;
            }
                this.updateUser();
                alert("Successfully updated manager info!");
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
            await axios.put('/WebShop/rest/user/updatemanager',userToUpdate)
					.then(response => {
						let user = response.data;
                        window.localStorage.setItem('User',JSON.stringify(user));
                        this.setCurrentUser();
					});
        },

        changeVisibility: function(){
            this.activeSubmenu = this.selectedButton;
        },

        detailsClicked: async function(selectedRestaurant){
            this.selectedRestaurant = selectedRestaurant;
            this.activeSubmenu = 'restaurantDetails';
            await this.loadComments(selectedRestaurant);
            await this.loadItems(selectedRestaurant);
        },

         loadComments: async function(selectedRestaurant){
            return await axios.get('/WebShop/rest/getcomments/' +selectedRestaurant.id)
						.then(response => {
							this.restaurantComments = response.data;
						});
        },

        loadItems: async function(selectedRestaurant){
            await axios.get('/WebShop/rest/getitemsforrestaurant/' + selectedRestaurant.id)
						.then(response => {
							this.restaurantItems = response.data;
						});
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

        preparedOrder: function(order){
            order.status = 'WAITING DELIVERY';//toastDeliverSuccess
            alert("PREPARED");
        },
        prepareOrder: function(order){
            order.status='IN PREPARATION';//toastDeliveryRequested
            alert("STARTED PREPARING");
        },
        acceptOrder: function(order){
            order.status = 'IN TRANSPORT';//toastDeliverSuccess
            alert("Accepted");
        },
        rejectOrder: function(order){
            order.status = 'WAITING DELIVERY';//toastDeliverSuccess
            alert("rejected");
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
            window.localStorage.removeItem('User');
            window.location.replace("http://localhost:8080/WebShop/")
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