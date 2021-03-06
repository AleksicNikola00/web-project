//visible : restaurants, viewCart, myProfile, orders, specificRestaurant, comment, registerLogin

var webShop = new Vue({
    el: '#dashboard',
    data: {
        restaurants : [],
        receivedRestaurants : [],
        selectedButton : {},
        currentUser : {},
        tempCurrUser : {},
		shopperTypeInfo : {},
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
        selectedRestaurant : {},
        commentsForRestaurant : [],
        menuForRestaurant : [],
        cart : [],
        lastInputFoodQuantity : {},
        pastOrders : [],
        pastReceivedOrders : [],
        orderFilterObj : {
            isOnlyNotDelievered : false,
            restaurant : '',
            bottomPrice : 0,
            upperPrice : 0,
            bottomDate : new Date(),
            upperDate : new Date(),
            orderStatus : '',
            restaurantType : ''
        },
        selectedRestaurantForComment : {},
        comment : {
            text : '',
            rating : '5'
        },
        validationMessage : '',
		tempRegisterUser : {
			username: '',
			password: '',
			firstname: '',
			lastname: '',
			dateOfBirth: '',
			gender: 'MALE',
		},
		registerMessage : '',
		tempLoginUser : {
			username : '',
			password : ''
		},
		loginMessage : ''
    },
    created (){
    },
    async mounted (){
        let user = window.localStorage.getItem('User');
		if (user != undefined){
        	this.currentUser = JSON.parse(user);	
		}
		else {
			this.currentUser = {
				username : '',
				firstname : '',
				lastname : '',
				gender : '',
				dateOfBirth : '',
				points : '0'
			}
		}
		
		if (this.currentUser.username != ''){
			await this.requestTypeOfShopper();
			await this.requestPastOrders();
		}
		else {
			this.shopperTypeInfo = {
				logoPath : '../Images/bronze-member.png',
				typeName : 'BRONZE',
				discount : 0 
			}
		}
		
		/* Setting date in date picker */
        var now = new Date();
        var first = new Date();
        first.setFullYear(now.getFullYear() - 100);

        maxDate = now.toISOString().substring(0,10);
        minDate = first.toISOString().substring(0,10);

		this.tempRegisterUser.dateOfBirth = maxDate;

		let datepic = $("#datepicker12");

        datepic.prop('max',maxDate);
        datepic.prop('min',minDate);
		
		await this.requestRestaurants();
		
        //Actual
        this.orderFilterObj.bottomDate = new Date(0);
        this.orderFilterObj.upperDate = new Date() + 1;
        this.restaurants = this.receivedRestaurants.filter(rest => rest.name.includes(''));
		this.restaurants = this.sortIsOpen(this.restaurants);
        this.selectSubmenu(this.visible);

        this.tempCurrUser = Object.assign({}, this.currentUser);
        this.tempCurrUser.dateOfBirth = this.convertDate(this.tempCurrUser.dateOfBirth);
        this.tempCurrUser.points = parseInt(this.tempCurrUser.points);
        this.tempCurrUser.newPass = '';
        this.tempCurrUser.oldPass = '';
        this.selectStatus();
        
    },
    methods : {
		async requestTypeOfShopper(){
			return await axios.get('/WebShop/rest/user/getshoppertype/' + this.currentUser.shopperType)
							.then(response =>{
								this.shopperTypeInfo = response.data;
							});	
		},
        async requestRestaurants(){
			
			return await axios.get('/WebShop/rest/getrestaurants')
						.then(response => {
							this.receivedRestaurants = response.data;
						});
			
        },
		async requestPastOrders(){
			return await axios.get('/WebShop/rest/getorders/' + this.currentUser.username)
						.then(response => {
							this.pastReceivedOrders = response.data;
							for (order of this.pastReceivedOrders){
								order.status = order.status.replace("_"," ");
							}
							
							this.pastReceivedOrders.reverse();
						})
						.then(() => {
        					this.pastOrders = this.pastReceivedOrders.filter(order => order.restaurantName.includes(''));
						});
		},
		async requestUserData(){
			return await axios.get('/WebShop/rest/user/getbyuser/' + this.currentUser.username)
						.then(response => {
							this.currentUser = response.data;
							window.localStorage.setItem('User', JSON.stringify(response.data));
							this.tempCurrUser = Object.assign({}, this.currentUser);
							this.tempCurrUser.dateOfBirth = this.convertDate(this.tempCurrUser.dateOfBirth);
					        this.tempCurrUser.points = parseInt(this.tempCurrUser.points);
					        this.tempCurrUser.newPass = '';
					        this.tempCurrUser.oldPass = '';
						});
		},
        logout : function(){
            this.currentUser = undefined;
            this.tempCurrUser = undefined;
            window.localStorage.removeItem('User');

            window.location.replace("http://localhost:8080/WebShop/")
        },
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

            if (Object.keys(this.selectedButton).length != 0){
                this.selectedButton.removeClass('btn-primary');
                this.selectedButton.addClass('btn-light');
            }
            
            this.selectedButton = $('#' + actualSubmenu);
            this.selectedButton.removeClass('btn-light');
            this.selectedButton.addClass('btn-primary');

			if (submenu == 'myProfile' && this.currentUser.username == ''){
				this.visible = 'registerLogin';
				return;
			}
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
        calculateDate : function(date) {
            let result = new Date();
            
            let specificTime = date.split(' ')[0];
            let specificDate = date.split(' ')[1];

            result.setHours(parseInt(specificTime.split(':')[0]), parseInt(specificTime.split(':')[1]));

            result.setFullYear(parseInt(specificDate.split('-')[2]), parseInt(specificDate.split('-')[0]) - 1, parseInt(specificDate.split('-')[1]));

            return result;
        },
        sortIsOpen : function(restaurants){
            let result = restaurants.slice();

			result = result.sort(function(x, y){
                	return (x.open == y.open)? 0 : y? -1 : 1;
            });
			
            
            return result;
        },
        filterName : function(restaurants) {
            let result;
            result = restaurants.filter(rest => rest.name.toLowerCase().includes(this.filterObj.name.toLowerCase()));
            result.sort(function(a, b){
                return ('' + a.name).localeCompare(b.name);
            });
			
            return result;
        },
        filterType : function(restaurants) {
            let result;
            result = restaurants.filter(rest => rest.type.toLowerCase().includes(this.filterObj.type.toLowerCase()));
            result.sort(function(a, b){
                return ('' + a.type).localeCompare(b.type);
            });
			
			
            return result;
        },
        filterLocation : function(restaurants){
            let result;
            result = restaurants.filter(rest => rest.address.toLowerCase().includes(this.filterObj.location.toLowerCase()));
            result.sort(function(a, b){
                	return ('' + a.location).localeCompare(b.location);
            });


            return result;
        },
        filterRating : function(restaurants){
            let result;
            result = restaurants.filter(rest => parseFloat(rest.rating) >= parseFloat(this.filterObj.rating));
            result.sort(function(a, b){
                return a.rating - b.rating;
            });
			
			
            return result;
        },
        filterIsOpen : function(restaurants){
            let result;
            result = restaurants.filter(rest => rest.open == true);

            return result;
        },
		resetPastOrderFilters() {
			this.pastOrders = Object.assign({}, this.pastReceivedOrders);
		},
		getRestByCurr : function(isOpen){
			let ret = [];
			for (rest of this.receivedRestaurants){
				if (rest.open == isOpen){
					ret.push(rest);
				}
			}
			
			return ret;
		},
        doFilter : function(){
			let result = this.getRestByCurr(true);
			let resultClosed = this.getRestByCurr(false);
			
            if (this.filterObj.rating != 'all'){
                result = this.filterRating(result);
				resultClosed = this.filterRating(resultClosed);
            }
            if (this.filterObj.type != ''){
                result = this.filterType(result);
				resultClosed = this.filterType(resultClosed);
            }
            if (this.filterObj.location != ''){
                result = this.filterLocation(result);
				resultClosed = this.filterLocation(resultClosed);
            }
            if (this.filterObj.name != ''){
                result = this.filterName(result);
				resultClosed = this.filterName(resultClosed);
            }
			
			if (this.filterObj.isDesc){
				result.reverse();
				resultClosed.reverse();
			}
			
			if (this.filterObj.isOpen){
            	this.restaurants = result;
			} else {
				this.restaurants = result.concat(resultClosed);
			}
        },
        async pressedRestaurant(restaurant, index){
            if(restaurant.open == false){
                let toast = $('#restToast' + index);
                toast.toast('show');
                return;
            }
			
			await axios.get('/WebShop/rest/getitemsforrestaurant/' + restaurant.id)
						.then(response => {
							this.menuForRestaurant = response.data;
						});
						
			await axios.get('/WebShop/rest/getcomments/' + restaurant.id)
						.then(response => {
							this.commentsForRestaurant = response.data;
						});
						
			
            this.visible = 'specificRestaurant';
            this.selectedRestaurant = restaurant;
            this.selectSubmenu(this.visible);
        },

        calculateTotal : function(){
            let total = 0.0;

            for(const item of this.cart){
                total += item.price * item.amount;
            }
			
            return total * (1 - this.shopperTypeInfo.discount);
        },

        removeFromCart : function(index){
            this.cart.splice(index,1);
        },

        addToCart : function(index) {
            let input = $('input[name="inputs"]')[index].value;
            $('input[name="inputs"]')[index].value = ''; 
            
            if (input.includes('.')){
                this.lastInputFoodQuantity = input;
                let toastFail = $('#toastFail' + index);
                toastFail.toast('show');
                return;
            }

            if (input == '' || input == '0' || parseFloat(input) < 0){
                let toastFail = $('#toastFailEmpty' + index);
                toastFail.toast('show');
                return;
            }

            $('input[name="inputs"]')[index].value = '';       
            let currentItem = this.menuForRestaurant[index];

            let newItem = {
                name : currentItem.name,
                amount : input,
                price : currentItem.price,
                picturePath : currentItem.picturePath,
                restaurant : this.selectedRestaurant.name,
				restaurantId : currentItem.restaurantId,
				id : currentItem.id
            };

            let toast = $('#toast' + index);
            toast.toast('show');

            for (item of this.cart){
                if (item.restaurant == newItem.restaurant && item.name == newItem.name){
                    item.amount = parseInt(item.amount) + parseInt(newItem.amount);
                    return;
                }
            }

            this.cart.push(newItem);
        },

        doTotal : function(items){
            let total = 0;

            for(item of items){
                total += parseFloat(item.price) * parseInt(item.amount);
            }

            return total;
        },

        isOnlyNotDelieveredClick : function() {
            this.orderFilterObj.isOnlyNotDelievered = !this.orderFilterObj.isOnlyNotDelievered;
        },

        orderFilterRestName : function(orders){
            let result = orders.filter(order => order.restaurantName.toLowerCase().includes(this.orderFilterObj.restaurant.toLowerCase()));

            return result;
        },
        orderFilterBottomPrice : function(orders){
            let result = orders.filter(order => order.price >= parseFloat(this.orderFilterObj.bottomPrice));

            return result;
        },
        orderFilterUpperPrice : function(orders){
            let result = orders.filter(order => order.price <= parseFloat(this.orderFilterObj.upperPrice));

            return result;
        },
        orderFilterBottomDate : function(orders){
            let result = orders.filter(order => this.calculateDate(order.date) >= Date.parse(this.orderFilterObj.bottomDate));

            return result;
        },
        orderFilterUpperDate : function(orders){
            let result = orders.filter(order => this.calculateDate(order.date) <= Date.parse(this.orderFilterObj.upperDate));

            return result;
        },
        orderFilterStatus : function(orders){
            let result = orders.filter(order => order.status.toLowerCase().includes(this.orderFilterObj.orderStatus.toLowerCase()));

            return result;
        },
        orderFilterRestType : function(orders){
            let result = orders.filter(order => order.restaurantType.toLowerCase().includes(this.orderFilterObj.restaurantType.toLowerCase()));

            return result;
        },
        orderFilterOnlyNotDelievered : function(orders){
            let result = orders.filter(order => order.status.toLowerCase() != 'delievered');

            return result;
        },

        doOrderFilter : function() {
            let result = this.pastReceivedOrders;


            if (Date.parse(this.orderFilterObj.bottomDate) < new Date() && this.orderFilterObj.bottomDate != '' && this.orderFilterObj.bottomDate != undefined){
                result = this.orderFilterBottomDate(result);
            }
            if (Date.parse(this.orderFilterObj.upperDate) < new Date() && Date.parse(this.orderFilterObj.upperDate) > Date.parse(this.orderFilterObj.bottomDate)
					&& this.orderFilterObj.upperDate != '' || this.orderFilterObj.upperDate != undefined){
                result = this.orderFilterUpperDate(result);
            }
            if (this.orderFilterObj.bottomPrice != '' && parseFloat(this.orderFilterObj.bottomPrice) > 0){
                result = this.orderFilterBottomPrice(result);
            }
            if (this.orderFilterObj.upperPrice != '' && parseFloat(this.orderFilterObj.upperPrice) > 0){
                if (this.orderFilterObj.bottomPrice == '' || parseFloat(this.orderFilterObj.bottomPrice) < parseFloat(this.orderFilterObj.upperPrice)){
                    result = this.orderFilterUpperPrice(result);
                }
            }
            if (this.orderFilterObj.orderStatus != ''){
                result = this.orderFilterStatus(result);
            }
            if (this.orderFilterObj.restaurantType != ''){
                result = this.orderFilterRestType(result);
            }
            if (this.orderFilterObj.restaurant != ''){
                result = this.orderFilterRestName(result);
            }
            if (this.orderFilterObj.isOnlyNotDelievered){
                result = this.orderFilterOnlyNotDelievered(result);
            } 

            this.pastOrders = result;
        },

        rateRestaurant : function(restaurantName) {
            let restaurant;

            for(rest of this.receivedRestaurants){
                if (rest.name == restaurantName){
                    restaurant = rest;
                    break;
                }
            }

            this.selectedRestaurantForComment = restaurant;
            this.visible = 'comment';
        },
        submitComment : function(){
            if (this.comment.text.match(/^(\s*)$/)){
                $('#commentToastFail').toast('show');
                return;
            }

            let comment = this.comment.text.replace(/(\s+)/, ' ');
            let rating = this.comment.rating;
			
			axios.post('/WebShop/rest/comment/submit', {
				restaurantId : this.selectedRestaurantForComment.id,
				username : this.currentUser.username,
				text : comment,
				mark : rating
			});

            this.comment.text = '';
            this.comment.rating = '5';
			let temp = $('#commentToastSuccess');
            temp.toast('show');

			this.visible = 'orders';

        },

        validateString : function(input) {
            if (!input.match(/^(s*([a-zA-Z]+)s*)$/)){
                return 'incorrect input format';
            }

            return '';
        },

        validateDateOfBirth : function(input){
            let refDate = new Date();
            refDate.setFullYear(1920,0,1);

            if (refDate >= Date.parse(input)){
                return 'too old. Lowest date : 1.1.1920';
            }

            if (Date.parse(input) > new Date()){
                return 'too young. Date of birth is in the future';
            }

            return '';
        },
        validatePasswords : function() {
            if (this.tempCurrUser.oldPass == '' && this.tempCurrUser.newPass == ''){
                return '';
            }

            if (this.tempCurrUser.oldPass != '' && this.tempCurrUser.oldPass != this.currentUser.password){
                return 'Old password has to match the \none in the database';
            }

            if (this.tempCurrUser.oldPass != '' && this.tempCurrUser.newPass == ''){
                return 'New password is required if you \nwant to change. If not leave both password\nfields empty';
            }

            if (this.tempCurrUser.newPass != '' && this.tempCurrUser.oldPass == ''){
                return 'Cannot apply new password without\nold password entered to confirm identity';
            }

            if (this.tempCurrUser.newPass.length < 8){
                return 'New password has to be atleast 8 \ncharacters long';
            }

            return '';
        },
        checkIfAnythingChanged : function() {
            if (this.tempCurrUser.firstname != this.currentUser.firstname ||
                this.tempCurrUser.lastname != this.currentUser.lastname ||
                this.tempCurrUser.dateOfBirth != this.convertDate(this.currentUser.dateOfBirth) ||
                this.tempCurrUser.oldPass != '' || this.tempCurrUser.newPass != '')
                return true;

            return false;
        },

		async updateAccountDetails() {
			let userToUpdate = {
				username : this.tempCurrUser.username,
				name : this.tempCurrUser.firstname,
				surname : this.tempCurrUser.lastname,
				gender : this.tempCurrUser.gender,
				dateOfBirth : new Date(),
				password : this.tempCurrUser.newPass
			}
			
			let year = parseInt(this.tempCurrUser.dateOfBirth.split("-")[0]);
			let day = parseInt(this.tempCurrUser.dateOfBirth.split("-")[2]);
			let month = parseInt(this.tempCurrUser.dateOfBirth.split("-")[1]) - 1;
			
			userToUpdate.dateOfBirth = new Date(year,month,day);
			
			await axios.put('/WebShop/rest/user/updateshopper',userToUpdate)
					.then(response => {
						this.currentUser = response.data;
						this.tempCurrUser = Object.assign({},this.currentUser);
						this.tempCurrUser.dateOfBirth = this.convertDate(this.tempCurrUser.dateOfBirth);
						this.tempCurrUser.points = parseInt(this.tempCurrUser.points);
					    this.tempCurrUser.newPass = '';
					    this.tempCurrUser.oldPass = '';
					});
					
			await this.requestTypeOfShopper();
		},

        async validateAccountDetails() {
            if (!this.checkIfAnythingChanged())
                return;

            this.validationMessage = '';

            let firstnameValidation = this.validateString(this.tempCurrUser.firstname);
            if (firstnameValidation != ''){
                this.validationMessage += 'Firstname - ' + firstnameValidation + '\n';
            }

            let lastnameValidation = this.validateString(this.tempCurrUser.lastname);
            if (lastnameValidation != ''){
                this.validationMessage += 'Lastname - ' + lastnameValidation + '\n';
            }

            let dateValidation = this.validateDateOfBirth(this.tempCurrUser.dateOfBirth);
            if (dateValidation != ''){
                this.validationMessage += "Date of birth - " + dateValidation + '\n';
            }

            let validatePasswords = this.validatePasswords();
            if (validatePasswords != ''){
                this.validationMessage += "Passwords - " + validatePasswords + '\n';
            }

            if (this.validationMessage != ''){
                $('#accountToastFail').toast('show'); 
            }else{
                $('#accountToastSuccess').toast('show');
				this.updateAccountDetails();
            }


        },
		async createAndSubmitOrder(){
			let orders = new Array();
			for(item of this.cart){
				let handeled = false;
				
				for (order of orders){
					if (order.restaurant == item.restaurantId){
						order.orderedItems.push({
							id : item.id,
							amount : item.amount,
							price : item.price
						});
						handeled = true;
					}
				}
				
				if (!handeled){
					let newOrder = {
						orderedItems : new Array(),
						restaurant : item.restaurantId,
						username : this.currentUser.username,
						status : "PENDING"
					};
					
					newOrder.orderedItems.push({
						id : item.id,
						amount : item.amount,
						price : item.price
					});
					
					orders.push(newOrder);
				}
			}
			
			for (order of orders){
				let totalPrice = 0;
				for (item of order.orderedItems){
					totalPrice += item.price * item.amount;
					delete item.price;
				}
				
				order.price = totalPrice * (1 - this.shopperTypeInfo.discount);
			}
			
			await axios.post('/WebShop/rest/order/submitorder', orders);
			
			await this.requestPastOrders();
			
			await this.requestUserData();
			
			await this.requestTypeOfShopper();
		},
		async postOrder() {
			if (this.currentUser.username == ''){
				this.visible = 'registerLogin';
				return;
			}
			
			if (this.cart.length == 0){
				$('#sendOrderFail').toast('show');
				return;
			}
			
			await this.createAndSubmitOrder();
			
			$('#sendOrderSuccess').toast('show');
			this.cart = new Array();
			this.visible = 'restaurants';
		},
		
		async cancelOrder(pastOrder){
			
			await axios.get('/WebShop/rest/order/cancelorder/' + pastOrder.id);
			pastOrder.status = "CANCELED";
			
			await this.requestUserData();
			
			await this.requestTypeOfShopper();
		},
		
		async register(){
			let message = '';
			
			if (!this.tempRegisterUser.username.match(/^[\s]*[a-zA-Z0-9]+[\s]*$/)){
				message += "Username not in correct format! ";
			}
			if (this.validateString(this.tempRegisterUser.firstname)){
				message += "Name not in the correct format! ";
			}
			if (this.validateString(this.tempRegisterUser.lastname)){
				message += "Surname not in the correct format!";
			}
			
			if (message != ''){
				this.registerMessage = message;
				$("#registerFail").toast('show');
			}
			else {
				let newUser = {
					username : this.tempRegisterUser.username,
					password : this.tempRegisterUser.password,
					name : this.tempRegisterUser.firstname,
					surname : this.tempRegisterUser.lastname,
					gender : this.tempRegisterUser.gender,
					dateOfBirth : this.recoverDate(this.tempRegisterUser.dateOfBirth)
				}
				
				await this.registerNewUser(newUser);
			}
		},
		async registerNewUser(newUser){
			let vm = this;
			return await axios.post('/WebShop/rest/register', newUser)
						.then(response => {
							if (response.data.toString() != ''){
								vm.registerText = response.data.toString();
								$("#registerFail").toast('show');
							}
							else {
								vm.currentUser.username = newUser.username;
								vm.requestData();
								$("#registerSuccess").toast('show');
								vm.visible = 'restaurants';
							}
						});
			
		},
		async requestData(){
			await this.requestUserData();
			await this.requestPastOrders();
			await this.requestTypeOfShopper();
		},
		recoverDate(str){
			let date = new Date();
			
			let parts = str.split('-');
			
			let year = parseInt(parts[0]);
			let month = parseInt(parts[1] - 1);
			let day = parseInt(parts[2]);
			
			date.setFullYear(year, month, day);
			
			return date;
		},
		
		async login() {
			let message = '';
			
			if(this.tempLoginUser.username == ''){
				message += 'Username is required... ';
			}
			if (this.tempLoginUser.password == ''){ 
				message += "Password is required... ";
			}
			if (this.tempLoginUser.password.length < 8){
				message += "Password needs to be atleast 8 chars long!";
			}
			
			if (message != ''){
				this.loginMessage = message;
				$("#loginFail").toast('show');
			}
			else {
				let user = {
					username : this.tempLoginUser.username,
					password : this.tempLoginUser.password,
					role : "SHOPPER"
				}
				await this.loginUser(user);
			}
		},
		
		async loginUser(user){
			let vm = this;
			$.post({
				url: '/WebShop/rest/login',
				data: JSON.stringify({username: this.tempLoginUser.username, password: this.tempLoginUser.password, role: "SHOPPER"}),
				contentType: 'application/json',
				success: function(data) {
	                if (!data.includes("{")){
						vm.loginMessage = data;
	                    $("#loginFail").toast('show');
	                } else{
						let user = JSON.parse(data);
	                    vm.currentUser.username = user.username;
						vm.requestData();
						$("#loginSuccess").toast('show');
						vm.visible = 'restaurants';
	                }
				}
			});
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
        },

        orderfilterRestaurant() {
            return this.orderFilterObj.restaurant;
        },
        orderfilterIsShowNotDelievered(){
            return this.orderFilterObj.isOnlyNotDelievered;
        },
        orderfilterPriceRangeBottom() {
            return this.orderFilterObj.bottomPrice;
        },
        orderfilterPriceRangeUpper() {
            return this.orderFilterObj.upperPrice;
        },
        orderfilterDateRangeBottom() {
            return this.orderFilterObj.bottomDate;
        },
        orderfilterDateRangeUpper() {
            return this.orderFilterObj.upperDate;
        },
        orderfilterStatus(){
            return this.orderFilterObj.orderStatus;
        },
        orderfilterRestaurantType (){
            return this.orderFilterObj.restaurantType;
        },
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
        },

        orderfilterRestaurant(){
            this.doOrderFilter();
        },
        orderfilterIsShowNotDelievered(){
            this.doOrderFilter();
        },
        orderfilterPriceRangeBottom() {
            this.doOrderFilter();
        },
        orderfilterPriceRangeUpper() {
            this.doOrderFilter();
        },
        orderfilterDateRangeBottom() {
            this.doOrderFilter();
        },
        orderfilterDateRangeUpper() {
            this.doOrderFilter();
        },
        orderfilterStatus(){
            this.doOrderFilter();
        },
        orderfilterRestaurantType (){
            this.doOrderFilter();
        }
    }
})