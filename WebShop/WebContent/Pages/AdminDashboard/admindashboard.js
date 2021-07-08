//visible : restaurants, specificRestaurant, addEditRestaurant, users, addEditUser
//userVisible : shoppers, managers, deliveryWorkers, admin

function displaySubmenu(submenu) {
    $("div[name='" + submenu + "']").slideToggle(700);
}

function openDropDown(dropdown) {
	if (webShop.tempRestaurant.cameFrom == 'editRest'){
		return;
	}
	
    if ($("div[name='" + dropdown + "']").css('background-image').includes('angleDown.png')) {
        $("div[name='" + dropdown + "']").css('background-image', 'url("../Images/angleUp.png")');
    }
    else {
        $("div[name='" + dropdown + "']").css('background-image', 'url("../Images/angleDown.png")');
    }

    $("div[name='" + dropdown + "dropdown']").slideToggle(700);
}

function changeTag(tag) {
    $("button[name='tags']").removeClass('selected-tag');

    $("#" + tag).addClass('selected-tag');
}

/* view */
var webShop = new Vue({
    el: '#dashboard',
    data: {
        visible: 'restaurants',
        userVisible: 'shoppers',
        currentUser: {},
        receivedRestaurants: [],
        availableManagers : [],
        restaurants: [],
        restaurantFilterObj: {
            name: '',
            type: '',
            location: '',
            mark: 'All marks',
            ascDes: 'Ascending'
        },
        selectedRestaurant: {},
        items: [],
        comments: [],
        tempRestaurant: {
            name: '',
            type: '',
            location: '',
            geoLocation: '',
            logo: '',
            managerId: '',
			cameFrom : ''
        },
        allTypes: [
            'Turkish',
            'Greek',
            'Italian',
            'Pub',
            'Barbecue'
        ],
        receivedShoppers: [],
        shoppers: [],
        shopperFilterObj: {
            name: '',
            surname: '',
            username: '',
            ascDes: 'Ascending',
            points: '',
            shopperType: 'ALL',
        },

        receivedManagers: [],
        managers: [],
        managerFilterObj: {
            name: '',
            surname: '',
            username: '',
            ascDes: 'Ascending'
        },

        receivedDeliveryWorkers: [],
        deliveryWorkers: [],
        deliveryWorkerFilterObj: {
            name: '',
            surname: '',
            username: '',
            ascDes: 'Ascending'
        },

        receivedAdmins: [],
        admins: [],
        adminFilterObj: {
            name: '',
            surname: '',
            username: '',
            ascDes: 'Ascending',
            criteria : '',
        },

        manipulatedUser: {
            username: '',
            password: '',
            firstname: '',
            lastname: '',
            dateOfBirth: '',
            gender: '',
            role: '',
            status: 'normal',
            cameFrom: 'addUser'
        },

        notificationText: '',

        map: {},
        vectorLayer: {}
    },
    created() {
    },
    async mounted() {
        let user = window.localStorage.getItem('User');
        this.currentUser = JSON.parse(user);	
		this.currentUser.dateOfBirth = this.convertDate(this.currentUser.dateOfBirth);

        this.restaurants = Object.assign({}, this.receivedRestaurants);
        this.shoppers = Object.assign({}, this.receivedShoppers);
        this.managers = Object.assign({}, this.receivedManagers);
        this.deliveryWorkers = Object.assign({}, this.receivedDeliveryWorkers);
        this.admins = Object.assign({}, this.receivedAdmins);

		/* Calling database */
		await this.getRestaurants();
		await this.getShoppers();
		await this.getManagers();
		await this.getDeliveryWorkers();
		await this.getAdmins();

        /* Setting date in date picker */
        var now = new Date();
        var first = new Date();
        first.setFullYear(now.getFullYear() - 100);

        maxDate = now.toISOString().substring(0,10);
        minDate = first.toISOString().substring(0,10);

        $("#datepicker").prop('max',maxDate);
        $("#datepicker").prop('min',minDate);

        this.changeDisplayedUsers('shoppers');

        this.selectedRestaurant = this.restaurants[0];

        this.instantiateMap([19.833549,45.267136]);
    },
    methods: {
	
		/* Data getting */
		async getRestaurants() {
			return await axios.get('/WebShop/rest/getrestaurants/admin')
							.then(response => {
								this.receivedRestaurants = response.data;
        						this.restaurants = Object.assign({}, this.receivedRestaurants);
							})
		},
		async getItemForRestaurant() {
			return await axios.get('/WebShop/rest/getitemsforrestaurant/all/' + this.selectedRestaurant.id)
							.then(response => {
								this.items = response.data;
							});
		},
		async getCommentsForRestaurant() {
			return await axios.get('/WebShop/rest/getcomments/all/' + this.selectedRestaurant.id)
							.then(response => {
								this.comments = response.data;
							});
		},
		async getShoppers() {
			return await axios.get('/WebShop/rest/adminuser/shoppers')
							.then(response => {
								this.receivedShoppers = new Array();
								let vm = this;
								response.data.forEach(shopper => {
									shopper.dateOfBirth = vm.convertDate(shopper.dateOfBirth);
									vm.receivedShoppers.push(shopper);
								});
								
								this.shoppers = Object.assign({}, this.receivedShoppers);
							});
		},
		async getManagers(){
			return await axios.get('/WebShop/rest/adminuser/managers')
							.then(response => {
								this.receivedManagers = new Array();
								this.availableManagers = new Array();
								let vm = this;
								response.data.forEach(manager => {
									manager.dateOfBirth = vm.convertDate(manager.dateOfBirth);
									if (manager.restaurant == ''){
										vm.availableManagers.push(manager);
									}
									vm.receivedManagers.push(manager);
								});
								
								this.managers = Object.assign({}, this.receivedManagers);
							});
			
		},
		async getDeliveryWorkers() {
			return await axios.get('/WebShop/rest/adminuser/deliveryworkers')
							.then(response => {
								this.receivedDeliveryWorkers = new Array();
								let vm = this;
								response.data.forEach(deliveryWorker => {
									deliveryWorker.dateOfBirth = vm.convertDate(deliveryWorker.dateOfBirth);
									vm.receivedDeliveryWorkers.push(deliveryWorker);
								});
								
								this.deliveryWorkers = Object.assign({}, this.receivedDeliveryWorkers);
							});
		},
		async getAdmins() {
			return await axios.get('/WebShop/rest/adminuser/admins')
							.then(response => {
								this.receivedAdmins = new Array();
								let vm = this;
								response.data.forEach(admin => {
									admin.dateOfBirth = vm.convertDate(admin.dateOfBirth);
									vm.receivedAdmins.push(admin);
								});
								
								this.admins = Object.assign({}, this.receivedAdmins);
							});
		},
		/* Front functions*/
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
        instantiateMap(coordinates) {
            this.map = new ol.Map({
                target: 'map-div',
                layers: [
                    new ol.layer.Tile({
                        source: new ol.source.OSM()
                    })
                ],
                view: new ol.View({
                    center: ol.proj.fromLonLat(coordinates),
                    zoom: 12,
                    minZoom: 10,
                    maxZoom: 17
                })
            });

            let vm = this;

            this.map.on('click', function (e) {
                vm.addPin(e.coordinate);
            });

            this.addPin(ol.proj.transform(coordinates, 'EPSG:4326', 'EPSG:3857'));
        },
        addPin(coordinates) {
            var x = coordinates[0];
            var y = coordinates[1];

            var Markers = { x: x, y: y };

            //addPin
            if (this.vectorLayer) {
                this.map.removeLayer(this.vectorLayer);
            }

            var x = Markers.x;
            var y = Markers.y;

            var iconFeature = new ol.Feature({
                geometry: new ol.geom.Point([x, y])
            });

            var iconStyle = new ol.style.Style({
                image: new ol.style.Icon({
                    anchor: [0.5, 1],
                    src: "../Images/locationPin.png"
                })
            });

            iconFeature.setStyle(iconStyle);

            var vectorSource = new ol.source.Vector({
                features: [iconFeature]
            });

            this.vectorLayer = new ol.layer.Vector({
                source: vectorSource
            });

            this.map.addLayer(this.vectorLayer);

            var lonlat = ol.proj.transform(coordinates, 'EPSG:3857', 'EPSG:4326');

            this.tempRestaurant.geoLocation = lonlat[0].toString().substring(0,6) + ', ' + lonlat[1].toString().substring(0,6);
        },
        changeFilterMark(value, dropdown) {
            this.restaurantFilterObj.mark = value;

            openDropDown(dropdown);
        },
        changeAscDes() {
            if (this.restaurantFilterObj.ascDes.toLowerCase().includes('ascending')) {
                this.restaurantFilterObj.ascDes = 'Descending';
                $("button[name='ascDescButton']").get(0).style.setProperty('background-image', 'url("../Images/angleDown.png")');
            }
            else {
                this.restaurantFilterObj.ascDes = 'Ascending';
                $("button[name='ascDescButton']").get(0).style.setProperty('background-image', 'url("../Images/angleUp.png")');
            }
        },
        changeAscDesUser() {
            if (this.userVisible == 'shoppers') {
                if (this.shopperFilterObj.ascDes.toLowerCase().includes('ascending')) {
                    this.shopperFilterObj.ascDes = 'Descending';
                    $("button[name='ascDescShopperButton']").get(0).style.setProperty('background-image', 'url("../Images/angleDown.png")');
                }
                else {
                    this.shopperFilterObj.ascDes = 'Ascending';
                    $("button[name='ascDescShopperButton']").get(0).style.setProperty('background-image', 'url("../Images/angleUp.png")');
                }
            }
            else if (this.userVisible == 'managers') {
                if (this.managerFilterObj.ascDes.toLowerCase().includes('ascending')) {
                    this.managerFilterObj.ascDes = 'Descending';
                    $("button[name='ascDescManagerButton']").get(0).style.setProperty('background-image', 'url("../Images/angleDown.png")');
                }
                else {
                    this.managerFilterObj.ascDes = 'Ascending';
                    $("button[name='ascDescManagerButton']").get(0).style.setProperty('background-image', 'url("../Images/angleUp.png")');
                }
            }
            else if (this.userVisible == 'deliveryWorkers') {
                if (this.deliveryWorkerFilterObj.ascDes.toLowerCase().includes('ascending')) {
                    this.deliveryWorkerFilterObj.ascDes = 'Descending';
                    $("button[name='ascDescDeliveryButton']").get(0).style.setProperty('background-image', 'url("../Images/angleDown.png")');
                }
                else {
                    this.deliveryWorkerFilterObj.ascDes = 'Ascending';
                    $("button[name='ascDescDeliveryButton']").get(0).style.setProperty('background-image', 'url("../Images/angleUp.png")');
                }
            }
            else if (this.userVisible == 'admins') {
                if (this.adminFilterObj.ascDes.toLowerCase().includes('ascending')) {
                    this.adminFilterObj.ascDes = 'Descending';
                    $("button[name='ascDescAdminButton']").get(0).style.setProperty('background-image', 'url("../Images/angleDown.png")');
                }
                else {
                    this.adminFilterObj.ascDes = 'Ascending';
                    $("button[name='ascDescAdminButton']").get(0).style.setProperty('background-image', 'url("../Images/angleUp.png")');
                }
            }
        },
        changeDisplay(view) {
            this.visible = view;
        },
        async displaySpecificRestaurant(restaurant) {
            this.selectedRestaurant = restaurant;
			
			await this.getItemForRestaurant();
			await this.getCommentsForRestaurant();

            this.visible = 'specificRestaurant';
        },
        chooseManager(value, dropdown) {
            this.tempRestaurant.managerId = value.username;

            openDropDown(dropdown);
        },
        chooseType(value, dropdown) {
            this.tempRestaurant.type = value;

            openDropDown(dropdown);
        },
        editRestaurantViewChange() {
            this.tempRestaurant = Object.assign({}, this.selectedRestaurant);

            loc = this.tempRestaurant.geoLocation.split(', ');
            coords = [parseFloat(loc[0]), parseFloat(loc[1])];

            this.addPin(ol.proj.transform(coords, 'EPSG:4326', 'EPSG:3857'));
            this.map.getView().setCenter(ol.proj.transform(coords, 'EPSG:4326', 'EPSG:3857'));
            
			this.tempRestaurant.cameFrom = 'editRest';
			
            this.visible = 'addEditRestaurant';
        },
        addRestaurantViewChange() {
            this.tempRestaurant = {
                id : '',
                name: '',
                type: '',
                location: '',
                geoLocation: '',
                logo: '',
                managerId: '',
				cameFrom: 'addRest'
            }

            this.tempRestaurant.geoLocation = '19.833549, 45.267136';
            coords = [19.833549,45.267136];

            this.addPin(ol.proj.transform(coords, 'EPSG:4326', 'EPSG:3857'));
            this.map.getView().setCenter(ol.proj.transform(coords, 'EPSG:4326', 'EPSG:3857'));

            this.visible = 'addEditRestaurant';
        },
        getManagerByNameSurnameById(username) {
            for (manager of this.receivedManagers) {
                if (username == manager.username) {
                    return manager.firstname + ' ' + manager.lastname;
                }
            }
        },
        changeDisplayedUsers(users) {
            this.userVisible = users;

            changeTag(users);
        },
        changeFilterShopperType(value, dropdown) {
            this.shopperFilterObj.shopperType = value;

            openDropDown(dropdown);
        },
        chooseGender(value, dropdown) {
            this.manipulatedUser.gender = value;

            openDropDown(dropdown);
        },
        displayRole() {
            return this.manipulatedUser.role.replace('_', ' ');
        },
        chooseRole(value, dropdown) {
            this.manipulatedUser.role = value;

            openDropDown(dropdown);
        },

        editShopper(shopper) {

            this.manipulatedUser.username = shopper.username;
            this.manipulatedUser.firstname = shopper.firstname;
            this.manipulatedUser.lastname = shopper.lastname;
            this.manipulatedUser.dateOfBirth = shopper.dateOfBirth;
            this.manipulatedUser.gender = (shopper.gender == "UNKNOWN") ? 'PREFER NOT TO SAY' : shopper.gender;
            this.manipulatedUser.status = shopper.status;
            this.manipulatedUser.password = '';
            this.manipulatedUser.role = "SHOPPER";
            this.manipulatedUser.cameFrom = 'editUser';

            this.visible = 'addEditUser';
            $("#username").css("pointer-events", 'none');
        },
        editManager(manager) {

            this.manipulatedUser.username = manager.username;
            this.manipulatedUser.firstname = manager.firstname;
            this.manipulatedUser.lastname = manager.lastname;
            this.manipulatedUser.dateOfBirth = manager.dateOfBirth;
            this.manipulatedUser.gender = (manager.gender == "UNKNOWN") ? 'PREFER NOT TO SAY' : manager.gender;
            this.manipulatedUser.password = '';
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.role = "MANAGER";
            this.manipulatedUser.cameFrom = 'editUser';

            this.visible = 'addEditUser';
            $("#username").css("pointer-events", 'none');
        },
        editDeliveryWorker(delWorker) {

            this.manipulatedUser.username = delWorker.username;
            this.manipulatedUser.firstname = delWorker.firstname;
            this.manipulatedUser.lastname = delWorker.lastname;
            this.manipulatedUser.dateOfBirth = delWorker.dateOfBirth;
            this.manipulatedUser.gender = (delWorker.gender == "UNKNOWN") ? 'PREFER NOT TO SAY' : delWorker.gender;
            this.manipulatedUser.password = '';
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.role = "DELIVERY";
            this.manipulatedUser.cameFrom = 'editUser';

            this.visible = 'addEditUser';
            $("#username").css("pointer-events", 'none');
        },
        addNewUser() {
			
            this.manipulatedUser.username = '';
            this.manipulatedUser.firstname = '';
            this.manipulatedUser.lastname = '';
            this.manipulatedUser.dateOfBirth = '';
            this.manipulatedUser.gender = '';
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.role = '';
            this.manipulatedUser.password = '';
            this.manipulatedUser.cameFrom = 'addUser';

            this.visible = 'addEditUser';
            $("#username").css("pointer-events", 'auto');
        },
        editMyAccount() {


            this.manipulatedUser.username = this.currentUser.username;
            this.manipulatedUser.firstname = this.currentUser.firstname;
            this.manipulatedUser.lastname = this.currentUser.lastname;
            this.manipulatedUser.dateOfBirth = this.currentUser.dateOfBirth;
            this.manipulatedUser.gender = (this.currentUser.gender == "UNKNOWN") ? 'PREFER NOT TO SAY' : this.currentUser.gender;
            this.manipulatedUser.password = '';
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.role = 'ADMIN';
            this.manipulatedUser.cameFrom = 'editMyAccount';

            this.visible = 'addEditUser';
            $("#username").css("pointer-events", 'none');
        },
		recoverDate(str){
			let date = new Date();
			
			let parts = str.split('-');
			
			let year = parseInt(parts[0]);
			let month = parseInt(parts[2] - 1);
			let day = parseInt(parts[1]);
			
			date.setFullYear(year, month, day);
			
			return date;
		},

        async postChanges() {

			let newUser = {
				name : this.manipulatedUser.firstname,
				surname : this.manipulatedUser.lastname,
				username : this.manipulatedUser.username,
				gender : this.manipulatedUser.gender,
				dateOfBirth : this.recoverDate(this.manipulatedUser.dateOfBirth),
				password : this.manipulatedUser.password,
				role : this.manipulatedUser.role
			}

			if (newUser.gender.includes('PREFER')){
				newUser.gender = "UNKNOWN";
			}

            if (this.manipulatedUser.cameFrom == 'addUser'){
				let vm = this;
				await axios.post('/WebShop/rest/adminusermanipulation/add', newUser)
							.then(response =>{
								if (response.data == ''){
									vm.notificationText = 'User successfully added!';

                					vm.visible = 'users';

									vm.postMessage();
								}
								else {
									vm.notificationText = response.data.toString();

                					vm.visible = 'users';

									vm.postMessage();
								}
							});
            }
            else if (this.manipulatedUser.cameFrom == 'editUser'){
				let vm = this;
				await axios.post('/WebShop/rest/adminusermanipulation/edit', newUser)
							.then(response =>{
								if (response.data == ''){
									vm.notificationText = 'User successfully edited!';
                
                					vm.visible = 'users';

									vm.postMessage();
								}
								else {
									vm.notificationText = response.data.toString();

                					vm.visible = 'users';

									vm.postMessage();
								}
							});
                
            }
            else if (this.manipulatedUser.cameFrom == 'editMyAccount'){
				let vm = this;
				await axios.post('/WebShop/rest/adminusermanipulation/editmyacc', newUser)
							.then(response =>{
								if (response.data == ''){
									vm.notificationText = 'Your account is successfully edited!';
                
                					vm.visible = 'users';

									vm.postMessage();
								}
								else {
									vm.notificationText = response.data.toString();

                					vm.visible = 'users';

									vm.postMessage();
								}
							});
            }
            else {
				let vm = this;
				await axios.post('/WebShop/rest/adminusermanipulation/add', newUser)
							.then(response =>{
								if (response.data == ''){
									vm.notificationText = 'User successfully added!';
                
                					vm.visible = 'addEditRestaurant';

									vm.postMessage();
								}
								else {
									vm.notificationText = response.data.toString();

                					vm.visible = 'addEditRestaurant';

									vm.postMessage();
								}
							});
            }
			
			
			if (newUser.role == "SHOPPER"){
				await this.getShoppers();
			}
			else if (newUser.role == "MANAGER"){
				await this.getManagers();
			}
			else if (newUser.role == "ADMIN"){
				let vm = this;
				await this.getAdmins()
					.then(() => {
						for(admin of vm.receivedAdmins){
							if (admin.username == vm.currentUser.username){
								vm.currentUser = admin;
								window.localStorage.setItem('User', JSON.stringify(admin));
							}
						}
					});
			}
			else {
				await this.getDeliveryWorkers();
			}
        },

        postMessage() {
            $("#notification").fadeIn(700, function () {
                setTimeout(function () {
                    $("#notification").fadeOut(700);
                }, 3000);
            });
        },

        addManagerFromRest() {
            this.manipulatedUser.username = '';
            this.manipulatedUser.firstname = '';
            this.manipulatedUser.lastname = '';
            this.manipulatedUser.dateOfBirth = '';
            this.manipulatedUser.gender = 'PREFER NOT TO SAY';
            this.manipulatedUser.role = 'MANAGER';
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.cameFrom = 'restaurant';

            this.visible = "addEditUser";

            $("#username").prop("disabled", false);
        },

        onFileChange(e){
            var files = e.target.files || e.dataTransfer.files;

            if (!files.length)
                return;

            this.createImage(files[0]);
        },

        createImage(file){
            var image = new Image();
            var reader = new FileReader();

            var vm = this;

            reader.onload = (e) => {
                vm.tempRestaurant.logoPath = e.target.result;
            };
            reader.readAsDataURL(file);
        },

        blockUser() {
            this.manipulatedUser.status = 'blocked';

            this.notificationText = 'User is now blocked. To unblock him press the yellow unblock button!';
            this.postMessage();

        },
        unblockUser() {
            this.manipulatedUser.status = 'normal';

            this.notificationText = 'User is now unblocked!';
            this.postMessage();
        },


		/* Posting restaurant data */
		//Adding restaurant
		async submitNewRestaurant(){
			let newRestaurant = {
				name: this.tempRestaurant.name.trim(),
				type: this.tempRestaurant.type.toUpperCase().trim(),
				location: this.tempRestaurant.location.trim().replace(",", ', '),
				geoLocation: this.tempRestaurant.geoLocation,
				managerId: this.tempRestaurant.managerId,
				logoPath: this.tempRestaurant.logoPath.split('png;base64,')[1]
			}
			
			newRestaurant.location = newRestaurant.location.replace(/[\s]+/, ' ');
			
			let vm = this;
			
			await axios.post('/WebShop/rest/restaurant/addrestaurant', newRestaurant)
						.then(response => {
							if (response.data.toString() != ''){
								vm.notificationText = response.data.toString();
							}
							else {
								vm.notificationText = 'Successfully added!';
							}
							
							vm.postMessage();
						});
						
			await this.getRestaurants();
			await this.getManagers();
		},
		
		
        /* Validation */

        //Restaurant validation
        async validateRestaurant(){
            let message = '';
            if (!this.tempRestaurant.name.match(/^([a-zA-Z0-9]|[\s])+$/)){
                message += 'Name is not in the correct format... ';
            }
            if (this.tempRestaurant.type == ''){
                message += "Type is also important! ";
            }
            if (this.tempRestaurant.location == ''){
                message += "Tell us the location! ";
            }
			if (!this.tempRestaurant.location.match(/^[a-zA-Z0-9]+([a-zA-Z0-9]+|[\s]+)*[','][\s]*[0-9]+$/)){
				message += "Location is not in the correct format! ";
			}
            if (this.tempRestaurant.managerId == ''){
                message += "Specify the manager for the restaurant...";
            }
            if (this.tempRestaurant.logoPath == ''){
                message += "It would be nice to have a logo for your restaurant too!";
            }


            if (message != ''){
                this.notificationText = message;
                this.postMessage();
            }
            else {
				if (this.tempRestaurant.cameFrom == 'addRest'){
                	await this.submitNewRestaurant();
				}
            }
        },

        //User validation 
        async validateUser() {
            let message = '';

            if (!this.manipulatedUser.username.match(/^([\s]*[a-zA-Z0-9]+[\s]*)$/)){
                message += 'Username is not in the correct format... ';
            }
            if (!this.manipulatedUser.firstname.match(/^([\s]*[a-zA-Z]+[\s]*)$/)){
                message += 'Name is not in the correct format... ';
            }
            if (!this.manipulatedUser.lastname.match(/^([\s]*[a-zA-Z]+[\s]*)$/)){
                message += 'Surname is not in the correct format... ';
            }
            if (this.manipulatedUser.dateOfBirth == ''){
                message += 'Tell us how you old want the user to be! ';
            }
            if (this.manipulatedUser.gender == ''){
                message += 'Also specify some gender... ';
            }
            if (this.manipulatedUser.role == ''){
                message += 'Users role is really important! ';
            }

            if (this.manipulatedUser.cameFrom == 'addUser'){
                if (this.manipulatedUser.password == ''){
                    message += 'And oh, password is important!';
                }
                else if (this.manipulatedUser.password.length < 8){
                    message += 'Make the password atleast 8 chars long...';
                }
            }
            else {
                if (this.manipulatedUser.password != ''){
                    if (this.manipulatedUser.password.length < 8){
                        message += 'Make the password atleast 8 chars long...';
                    }
                }
            }

            if (message != ''){
                this.notificationText = message;
                this.postMessage();
            }
            else {
                await this.postChanges();
            }
        },

        /* Filter parts */
        sortRestName(restaurants) {
            let result = restaurants.filter(rest => rest.name.toLowerCase().includes(this.restaurantFilterObj.name.toLowerCase()));

            return result;
        },
        sortRestType(restaurants){
            let result = restaurants.filter(rest => rest.type.toLowerCase().includes(this.restaurantFilterObj.type.toLowerCase()));

            return result;
        },
        sortRestLoc(restaurants){
            let result = restaurants.filter(rest => rest.location.toLowerCase().includes(this.restaurantFilterObj.location.toLowerCase()));

            return result;
        },
        sortRestMark(restaurants){
            let refNumber = parseFloat(this.restaurantFilterObj.mark);

            let result = restaurants.filter(rest => parseFloat(rest.rating) > refNumber);

            return result;
        },

        sortUserName(users, crit, ascDes){
            let result = users.filter(user => user.firstname.toLowerCase().includes(crit.toLowerCase()));

            return result;
        },
        sortUserSurname(users, crit, ascDes){
            let result = users.filter(user => user.lastname.toLowerCase().includes(crit.toLowerCase()));

            return result;
        },
        sortUserUsername(users,crit, ascDes){
            let result = users.filter(user => user.username.toLowerCase().includes(crit.toLowerCase()));

            return result;
        },
        sortShopperPoints(users){
            let ref = parseFloat(this.shopperFilterObj.points);
            let result = users.filter(user => user.collectedPoints > ref);

            return result;
        },
        sortShopperType(users){
            let result = users.filter(user => user.type.toLowerCase().includes(this.shopperFilterObj.shopperType.toLowerCase()));

            return result;
        },

        /* Filtering */
        filterRestaurants() {
            let result = this.receivedRestaurants.slice();

            if (this.restaurantFilterObj.type != ''){
                result = this.sortRestType(result);
            }
            if (this.restaurantFilterObj.location != ''){
                result = this.sortRestLoc(result);
            }
            if (!this.restaurantFilterObj.mark.toLowerCase().includes('all marks')){
                result = this.sortRestMark(result);
            }
            if (this.restaurantFilterObj.name != ''){
                result = this.sortRestName(result);
            }

            if (this.restaurantFilterObj.criteria == 'name'){
                result.sort(function(a, b){
                    return ('' + a.name).localeCompare(b.name);
                });

                if (this.restaurantFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }
            else if (this.restaurantFilterObj.criteria == 'type'){
                result.sort(function(a, b){
                    return ('' + a.type).localeCompare(b.type);
                });

                if (this.restaurantFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }
            else if (this.restaurantFilterObj.criteria == 'location'){
                result.sort(function(a, b){
                    return ('' + a.location).localeCompare(b.location);
                });

                if (this.restaurantFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }
            else if (this.restaurantFilterObj.criteria == 'mark'){
                result.sort(function(a,b){
                    return (parseFloat(a.rating) - parseFloat(b.rating));
                });

                if (this.restaurantFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }

            this.restaurants = result;
        },

        filterShoppers() {
            let result = this.receivedShoppers.slice();

            
            if (this.shopperFilterObj.shopperType != 'ALL'){
                result = this.sortShopperType(result);
            }
            if (this.shopperFilterObj.points != ''){
                result = this.sortShopperPoints(result);
            }
            if (this.shopperFilterObj.username != ''){
                result = this.sortUserUsername(result, this.shopperFilterObj.username, this.shopperFilterObj.ascDes);
            }
            if (this.shopperFilterObj.surname != ''){
                result = this.sortUserSurname(result, this.shopperFilterObj.surname, this.shopperFilterObj.ascDes);
            }
            if (this.shopperFilterObj.name != ''){
                result = this.sortUserName(result, this.shopperFilterObj.name, this.shopperFilterObj.ascDes);
            }

            if (this.shopperFilterObj.criteria == 'name'){
                result.sort(function(a, b){
                    return ('' + a.firstname).localeCompare(b.firstname);
                });

                if (this.shopperFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }
            else if (this.shopperFilterObj.criteria == 'surname'){
                result.sort(function(a, b){
                    return ('' + a.lastname).localeCompare(b.lastname);
                });

                if (this.shopperFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }
            else if (this.shopperFilterObj.criteria == 'username'){
                result.sort(function(a, b){
                    return ('' + a.username).localeCompare(b.username);
                });

                if (this.shopperFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }
            else if (this.shopperFilterObj.criteria == 'points'){
                result.sort(function(a,b){
                    return (parseFloat(a.collectedPoints) - parseFloat(b.collectedPoints));
                });

                if (this.shopperFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }
            
            this.shoppers = result;
        },

        filterManagers() {
            let result = this.receivedManagers.slice();

            if (this.managerFilterObj.username != ''){
                result = this.sortUserUsername(result, this.managerFilterObj.username, this.managerFilterObj.ascDes);
            }
            if (this.managerFilterObj.surname != ''){
                result = this.sortUserSurname(result, this.managerFilterObj.surname, this.managerFilterObj.ascDes);
            }
            if (this.managerFilterObj.name != ''){
                result = this.sortUserName(result, this.managerFilterObj.name, this.managerFilterObj.ascDes);
            }

            if (this.managerFilterObj.criteria == 'name'){
                result.sort(function(a, b){
                    return ('' + a.firstname).localeCompare(b.firstname);
                });

                if (this.managerFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }
            else if (this.managerFilterObj.criteria == 'surname'){
                result.sort(function(a, b){
                    return ('' + a.lastname).localeCompare(b.lastname);
                });

                if (this.managerFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }
            else if (this.managerFilterObj.criteria == 'username'){
                result.sort(function(a, b){
                    return ('' + a.username).localeCompare(b.username);
                });

                if (this.managerFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }

            this.managers = result;
        },

        filterDeliveryWorkers() {
            let result = this.receivedDeliveryWorkers.slice();

            if (this.deliveryWorkerFilterObj.username != ''){
                result = this.sortUserUsername(result, this.deliveryWorkerFilterObj.username, this.deliveryWorkerFilterObj.ascDes);
            }
            if (this.deliveryWorkerFilterObj.surname != ''){
                result = this.sortUserSurname(result, this.deliveryWorkerFilterObj.surname, this.deliveryWorkerFilterObj.ascDes);
            }
            if (this.deliveryWorkerFilterObj.name != ''){
                result = this.sortUserName(result, this.deliveryWorkerFilterObj.name, this.deliveryWorkerFilterObj.ascDes);
            }

            if (this.deliveryWorkerFilterObj.criteria == 'name'){
                result.sort(function(a, b){
                    return ('' + a.firstname).localeCompare(b.firstname);
                });

                if (this.deliveryWorkerFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }
            else if (this.deliveryWorkerFilterObj.criteria == 'surname'){
                result.sort(function(a, b){
                    return ('' + a.lastname).localeCompare(b.lastname);
                });

                if (this.deliveryWorkerFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }
            else if (this.deliveryWorkerFilterObj.criteria == 'username'){
                result.sort(function(a, b){
                    return ('' + a.username).localeCompare(b.username);
                });

                if (this.deliveryWorkerFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }

            this.deliveryWorkers = result;
        },

        filterAdmins(){
            let result = this.receivedAdmins.slice();

            if (this.adminFilterObj.username != ''){
                result = this.sortUserUsername(result, this.adminFilterObj.username, this.adminFilterObj.ascDes);
            }
            if (this.adminFilterObj.surname != ''){
                result = this.sortUserSurname(result, this.adminFilterObj.surname, this.adminFilterObj.ascDes);
            }
            if (this.adminFilterObj.name != ''){
                result = this.sortUserName(result, this.adminFilterObj.name, this.adminFilterObj.ascDes);
            }

            if (this.adminFilterObj.criteria == 'name'){
                result.sort(function(a, b){
                    return ('' + a.firstname).localeCompare(b.firstname);
                });

                if (this.adminFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }
            else if (this.adminFilterObj.criteria == 'surname'){
                result.sort(function(a, b){
                    return ('' + a.lastname).localeCompare(b.lastname);
                });

                if (this.adminFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }
            else if (this.adminFilterObj.criteria == 'username'){
                result.sort(function(a, b){
                    return ('' + a.username).localeCompare(b.username);
                });

                if (this.adminFilterObj.ascDes.toLowerCase().includes('descending')){
                    result.reverse();
                }
            }

            this.admins = result;
        },

        changeCriteria(criteria){
            let critParts = criteria.toString().split('_');

            if (critParts[0] == 'admin'){
                this.adminFilterObj.criteria = critParts[1];
            }
            else if (critParts[0] == 'shopper'){
                this.shopperFilterObj.criteria = critParts[1];
            }
            else if (critParts[0] == 'manager'){
                this.managerFilterObj.criteria = critParts[1];
            }
            else if (critParts[0] == 'restaurant'){
                this.restaurantFilterObj.criteria = critParts[1];
            }
            else {
                this.deliveryWorkerFilterObj.criteria = critParts[1];
            }

            $("#" + critParts[0] +"_name").css('border', 'none');
            $("#" + critParts[0] +"_surname").css('border', 'none');
            $("#" + critParts[0] +"_username").css('border', 'none');
            $("#" + critParts[0] +"_points").css('border', 'none');
            $("#" + critParts[0] +"_type").css('border', 'none');
            $("#" + critParts[0] +"_location").css('border', 'none');
            $("#" + critParts[0] +"_mark").css('border', 'none');

            if (critParts[1] == 'name'){
                $("#" + critParts[0] + "_name").css('border', '1px solid #021056');   
            }
            else if (critParts[1] == 'surname'){
                $("#" + critParts[0] + "_surname").css('border', '1px solid #021056');   
            }
            else if (critParts[1] == 'username'){
                $("#" + critParts[0] + "_username").css('border', '1px solid #021056');   
            }
            else if (critParts[1] == 'points'){
                $("#" + critParts[0] + "_points").css('border', '1px solid #021056'); 
            }
            else if (critParts[1] == 'location'){
                $("#" + critParts[0] + "_location").css('border', '1px solid #021056'); 
            }
            else if (critParts[1] == 'type'){
                $("#" + critParts[0] + "_type").css('border', '1px solid #021056'); 
            }
            else if (critParts[1] == 'mark'){
                $("#" + critParts[0] + "_mark").css('border', '1px solid #021056');
            }
        },

		logout() {
			this.currentUser = undefined;
            this.tempCurrUser = undefined;
            window.localStorage.removeItem('User');

            window.location.replace("http://localhost:8080/WebShop/");
		}
    },
    computed: {
        //Restaurants
        restaurantNameWatch() {
            return this.restaurantFilterObj.name;
        },
        restaurantTypeWatch() {
            return this.restaurantFilterObj.type;
        },
        restaurantLocationWatch() {
            return this.restaurantFilterObj.location;
        },
        restaurantMarkWatch() {
            return this.restaurantFilterObj.mark;
        },
        restaurantOrderWatch() {
            return this.restaurantFilterObj.ascDes;
        },
        //Shoppers
        shopperNameWatch() {
            return this.shopperFilterObj.name;
        },
        shopperSurnameWatch() {
            return this.shopperFilterObj.surname;
        },
        shopperUsernameWatch() {
            return this.shopperFilterObj.username;
        },
        shopperOrderWatch() {
            return this.shopperFilterObj.ascDes;
        },
        shopperPointsWatch() {
            return this.shopperFilterObj.points;
        },
        shopperTypeWatch() {
            return this.shopperFilterObj.shopperType;
        },

        //Managers
        managerNameWatch() {
            return this.managerFilterObj.name;
        },
        managerLastnameWatch(){
            return this.managerFilterObj.surname;
        },
        managerUsernameWatch(){
            return this.managerFilterObj.username;
        },
        managerOrderWatch() {
            return this.managerFilterObj.ascDes;
        },

        //DeliveryWorkers 
        deliveryWorkerNameWatch() {
            return this.deliveryWorkerFilterObj.name;
        },
        deliveryWorkerSurnameWatch() {
            return this.deliveryWorkerFilterObj.surname;
        },
        deliveryWorkerUsernameWatch() {
            return this.deliveryWorkerFilterObj.username;
        },
        deliveryWorkerOrderWatch() {
            return this.deliveryWorkerFilterObj.ascDes;
        },

        //Admins
        adminNameWatch(){
            return this.adminFilterObj.name;
        },
        adminSurnameWatch(){
            return this.adminFilterObj.surname;
        },
        adminUsernameWatch(){
            return this.adminFilterObj.username;
        },
        adminOrderWatch() {
            return this.adminFilterObj.ascDes;
        },
    },
    watch: {
        //Restaurants
        restaurantNameWatch(){
            this.filterRestaurants();
        },
        restaurantTypeWatch() {
            this.filterRestaurants();
        },
        restaurantLocationWatch() {
            this.filterRestaurants();
        },
        restaurantMarkWatch(){
            this.filterRestaurants();
        },
        restaurantOrderWatch(){
            this.filterRestaurants();
        },

        //Shopper 
        shopperNameWatch() {
            this.filterShoppers();
        },
        shopperSurnameWatch() {
            this.filterShoppers();
        },
        shopperUsernameWatch() {
            this.filterShoppers();
        },
        shopperOrderWatch() {
            this.filterShoppers();
        },
        shopperPointsWatch() {
            this.filterShoppers();
        },
        shopperTypeWatch() {
            this.filterShoppers();
        },

        //Managers
        managerNameWatch() {
            this.filterManagers();
        },
        managerLastnameWatch(){
            this.filterManagers();
        },
        managerUsernameWatch(){
            this.filterManagers();
        },
        managerOrderWatch() {
            this.filterManagers();
        },

        //DeliveryWorkers 
        deliveryWorkerNameWatch() {
            this.filterDeliveryWorkers();
        },
        deliveryWorkerSurnameWatch() {
            this.filterDeliveryWorkers();
        },
        deliveryWorkerUsernameWatch() {
            this.filterDeliveryWorkers();
        },
        deliveryWorkerOrderWatch() {
            this.filterDeliveryWorkers();
        },

        //Admins
        adminNameWatch(){
            this.filterAdmins();
        },
        adminSurnameWatch(){
            this.filterAdmins();
        },
        adminUsernameWatch(){
            this.filterAdmins();
        },
        adminOrderWatch() {
            this.filterAdmins();
        }
    }
})