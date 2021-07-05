//visible : restaurants, specificRestaurant, addEditRestaurant, users, addEditUser
//userVisible : shoppers, managers, deliveryWorkers, admin

function displaySubmenu(submenu) {
    $("div[name='" + submenu + "']").slideToggle(700);
}

function openDropDown(dropdown) {
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
        visible: 'users',
        userVisible: 'managers',
        currentUser: {},
        receivedRestaurants: [
            {
                name: 'First',
                type: 'Turkish',
                rating: '3.2',
                manager: 'Dzoni bova',
                location: 'Stevana Mokranjca 24',
                status: 'OPEN',
                geoLocation: '18, 45.267136',
                managerId: '1',
            },
            {
                name: 'Second',
                type: 'Italian',
                rating: '3.3',
                manager: 'Dzoni bova',
                location: 'Address one',
                status: 'OPEN',
                geoLocation: '86.3333, 100.233',
                managerId: '7',
            },
            {
                name: 'Thrid',
                type: 'Mexican',
                rating: '4.8',
                manager: 'Dzoni bova',
                location: 'Second Address',
                status: 'OPEN',
                geoLocation: '86.3333, 100.233',
                managerId: '6',
            },
            {
                name: 'Forth',
                type: 'Barbecue',
                rating: '2.4',
                manager: 'Dzoni bova',
                location: 'ABBBBA',
                status: 'OPEN',
                geoLocation: '86.3333, 100.233',
                managerId: '3',
            },
            {
                name: 'Fifth',
                type: 'Turkish',
                rating: '4.4',
                manager: 'Dzoni bova',
                location: 'CDCDCDCD',
                status: 'OPEN',
                geoLocation: 'LELELEL',
                managerId: '5',
            },
            {
                name: 'Sixth',
                type: 'Greek',
                rating: '2.2',
                manager: 'Dzoni bova',
                location: 'Stevana Mokranjca 24',
                status: 'OPEN',
                geoLocation: '86.3333, 100.233',
                managerId: '2',
            },
            {
                name: 'Seventh',
                type: 'Chinese',
                rating: '1.9',
                manager: 'Dzoni bova',
                location: 'Pasiceva',
                status: 'OPEN',
                geoLocation: '86.3333, 100.233',
                managerId: '4',
            }
        ],
        receivedUsers: [],
        users: [],
        restaurants: [],
        restaurantFilterObj: {
            name: '',
            type: '',
            location: '',
            mark: 'All marks',
            ascDes: 'Ascending'
        },
        selectedRestaurant: {},
        items: [
            {
                name: 'Pizza',
                price: '1000',
                amount: '700',
                type: 'FOOD',
                unit: 'GRAM',
                description: 'Some amazing pizza that has been created by the best chefs!'
            },
            {
                name: 'Pizza',
                price: '1000',
                amount: '700',
                type: 'FOOD',
                unit: 'GRAM',
                description: 'Some amazing pizza that has been created by the best chefs!'
            },
            {
                name: 'Pizza',
                price: '1000',
                amount: '700',
                type: 'FOOD',
                unit: 'GRAM',
                description: 'Some amazing pizza that has been created by the best chefs!'
            }
        ],
        comments: [
            {
                text: 'its really amazing lads go ahead and try!',
                username: 'ProSlayerXXX',
                mark: '5'
            },
            {
                text: 'Could be better i guess but whatevs bitches are good',
                username: 'NiggaHigga',
                mark: '3'
            },
        ],
        tempRestaurant: {
            name: '',
            type: '',
            location: '',
            geoLocation: '',
            logo: '',
            managerId: '1'
        },
        allTypes: [
            'Turkish',
            'Greek',
            'Italian',
            'Pub',
            'Barbecue'
        ],
        receivedShoppers: [
            {
                name: 'Nikola',
                surname: 'Milosavljevic',
                username: 'nikkiyuh',
                dateOfBirth: '1999-07-04',
                gender: 'MALE',
                collectedPoints: '1300',
                type: 'BRONZE',
                status: 'normal'
            },
            {
                name: 'Nikola',
                surname: 'Aleksic',
                username: 'otherNikky',
                dateOfBirth: '1940-07-02',
                gender: 'MALE',
                collectedPoints: '2600',
                type: 'SILVER',
                status: 'suspicious'
            },
            {
                name: 'Filler',
                surname: 'User',
                username: 'fillerUser',
                dateOfBirth: '1.1.1940',
                gender: 'MALE',
                collectedPoints: '2600',
                type: 'SILVER',
                status: 'blocked'
            },
            {
                name: 'Filler',
                surname: 'User',
                username: 'fillerUser',
                dateOfBirth: '1.1.1940',
                gender: 'MALE',
                collectedPoints: '2600',
                type: 'SILVER',
                status: 'normal'
            },
            {
                name: 'Filler',
                surname: 'User',
                username: 'fillerUser',
                dateOfBirth: '1.1.1940',
                gender: 'MALE',
                collectedPoints: '2600',
                type: 'SILVER',
                status: 'suspicious'
            },
            {
                name: 'Filler',
                surname: 'User',
                username: 'fillerUser',
                dateOfBirth: '1.1.1940',
                gender: 'MALE',
                collectedPoints: '2600',
                type: 'SILVER',
                status: 'normal'
            },
            {
                name: 'Filler',
                surname: 'User',
                username: 'fillerUser',
                dateOfBirth: '1.1.1940',
                gender: 'MALE',
                collectedPoints: '2600',
                type: 'SILVER',
                status: 'suspicious'
            },
            {
                name: 'Filler',
                surname: 'User',
                username: 'fillerUser',
                dateOfBirth: '1.1.1940',
                gender: 'MALE',
                collectedPoints: '2600',
                type: 'SILVER',
                status: 'blocked'
            },
            {
                name: 'Filler',
                surname: 'User',
                username: 'fillerUser',
                dateOfBirth: '1.1.1940',
                gender: 'MALE',
                collectedPoints: '2600',
                type: 'SILVER',
                status: 'normal'
            },
            {
                name: 'Filler',
                surname: 'User',
                username: 'fillerUser',
                dateOfBirth: '1.1.1940',
                gender: 'MALE',
                collectedPoints: '2600',
                type: 'SILVER',
                status: 'suspicious'
            }
        ],
        shoppers: [],
        shopperFilterObj: {
            name: '',
            surname: '',
            username: '',
            ascDes: 'Ascending',
            points: '',
            shopperType: 'ALL',
        },

        receivedManagers: [
            {
                name: 'Dzoni',
                surname: 'Stagod',
                dateOfBirth: '1941-05-26',
                gender: 'MALE',
                restaurant: 'Ciao',
                username: 'nikkiyyuh',
                id : '1'
            },
            {
                name: 'Dzoni',
                surname: 'Drugi',
                dateOfBirth: '1941-05-26',
                gender: 'MALE',
                restaurant: '',
                username: 'nikkiyyuh',
                id : '2'
            }
        ],
        managers: [],
        managerFilterObj: {
            name: '',
            surname: '',
            username: '',
            ascDes: 'Ascending'
        },

        receivedDeliveryWorkers: [
            {
                name: 'Nikola',
                surname: 'Milosavljevic',
                username: 'nikkiyuh',
                dateOfBirth: '4.7.1999.',
                gender: 'MALE'
            },
            {
                name: 'Drugi',
                surname: 'Drugic',
                username: 'nikkiyuhfsfaf',
                dateOfBirth: '4.7.1999.',
                gender: 'MALE'
            },
            {
                name: 'Treci',
                surname: 'fsfsAAA',
                username: 'sfasfasggg',
                dateOfBirth: '4.7.1999.',
                gender: 'MALE'
            },
        ],
        deliveryWorkers: [
            
        ],
        deliveryWorkerFilterObj: {
            name: '',
            surname: '',
            username: '',
            ascDes: 'Ascending'
        },

        receivedAdmins: [
            {
                name: 'Nikola',
                surname: 'Milosavljevic',
                dateOfBirth: '4.7.1999.',
                username: 'nikkiyuh',
                gender: 'MALE'
            },
            {
                name: 'Krki',
                surname: 'Males',
                dateOfBirth: '4.7.1999.',
                username: 'nikkiyuh',
                gender: 'MALE'
            },
            {
                name: 'Nikola',
                surname: 'Ment',
                dateOfBirth: '4.7.1999.',
                username: 'nikkiyuh',
                gender: 'MALE'
            }
        ],
        admins: [],
        adminFilterObj: {
            name: '',
            surname: '',
            username: '',
            ascDes: 'Ascending'
        },

        manipulatedUser: {
            username: 'some username',
            password: '',
            name: 'some name',
            surname: 'some surname',
            dateOfBirth: '1999-07-04',
            gender: 'MALE',
            role: 'ADMIN',
            status: 'normal',
            cameFrom: 'editUser'
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
        this.currentUser = {};
        this.currentUser.username = 'curr';

        this.restaurants = Object.assign({}, this.receivedRestaurants);
        this.shoppers = Object.assign({}, this.receivedShoppers);
        this.managers = Object.assign({}, this.receivedManagers);
        this.deliveryWorkers = Object.assign({}, this.receivedDeliveryWorkers);
        this.admins = Object.assign({}, this.receivedAdmins);

        this.changeDisplayedUsers('admins');

        this.selectedRestaurant = this.restaurants[0];

        this.instantiateMap([19.833549,45.267136]);
    },
    methods: {
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
        displaySpecificRestaurant(restaurant) {
            this.selectedRestaurant = restaurant;
            this.visible = 'specificRestaurant';
        },
        chooseManager(value, dropdown) {
            this.tempRestaurant.managerId = value.id;

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
            

            this.visible = 'addEditRestaurant';
        },
        addRestaurantViewChange() {
            this.tempRestaurant = {
                name: '',
                type: '',
                location: '',
                geoLocation: '',
                logo: '',
                managerId: ''
            }

            this.visible = 'addEditRestaurant';
        },
        getManagerByNameSurnameById(id) {
            for (manager of this.receivedManagers) {
                if (id == manager.id) {
                    return manager.name + ' ' + manager.surname;
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
            this.manipulatedUser.name = shopper.name;
            this.manipulatedUser.surname = shopper.surname;
            this.manipulatedUser.dateOfBirth = shopper.dateOfBirth;
            this.manipulatedUser.gender = shopper.gender;
            this.manipulatedUser.status = shopper.status;
            this.manipulatedUser.role = "SHOPPER";
            this.manipulatedUser.cameFrom = 'editUser';

            this.visible = 'addEditUser';
        },
        editManager(manager) {
            this.manipulatedUser.username = manager.username;
            this.manipulatedUser.name = manager.name;
            this.manipulatedUser.surname = manager.surname;
            this.manipulatedUser.dateOfBirth = manager.dateOfBirth;
            this.manipulatedUser.gender = manager.gender;
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.role = "MANAGER";
            this.manipulatedUser.cameFrom = 'editUser';

            this.visible = 'addEditUser';
        },
        editDeliveryWorker(delWorker) {
            this.manipulatedUser.username = delWorker.username;
            this.manipulatedUser.name = delWorker.name;
            this.manipulatedUser.surname = delWorker.surname;
            this.manipulatedUser.dateOfBirth = delWorker.dateOfBirth;
            this.manipulatedUser.gender = delWorker.gender;
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.role = "DELIVERY_WORKER";
            this.manipulatedUser.cameFrom = 'editUser';

            this.visible = 'addEditUser';
        },
        addNewUser() {
            this.manipulatedUser.username = '';
            this.manipulatedUser.name = '';
            this.manipulatedUser.surname = '';
            this.manipulatedUser.dateOfBirth = '';
            this.manipulatedUser.gender = '';
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.role = '';
            this.manipulatedUser.cameFrom = 'addUser';

            this.visible = 'addEditUser';
        },
        editMyAccount() {

            $("#username").prop("disabled", true);

            this.manipulatedUser.username = this.currentUser.username;
            this.manipulatedUser.name = this.currentUser.username;
            this.manipulatedUser.surname = this.currentUser.username;
            this.manipulatedUser.dateOfBirth = this.currentUser.username;
            this.manipulatedUser.gender = this.currentUser.username;
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.role = 'ADMIN';
            this.manipulatedUser.cameFrom = 'editMyAccount';

            this.visible = 'addEditUser';
        },
        postChanges() {

            if (this.manipulatedUser.cameFrom == 'addUser'){
                this.notificationText = 'User successfully added!';

                this.visible = 'users';
            }
            else if (this.manipulatedUser.cameFrom == 'editUser'){
                this.notificationText = 'User successfully edited!';
                
                this.visible = 'users';
            }
            else if (this.manipulatedUser.cameFrom == 'editMyAccount'){
                this.notificationText = 'Your account is successfully edited!';

            }
            else {
                this.notificationText = 'User successfully added!';

                this.visible = 'addEditRestaurant';
            }

            $("#notification").fadeIn(700, function () {
                setTimeout(function () {
                    $("#notification").fadeOut(700);
                }, 3000);
            });
        },

        addManagerFromRest() {
            this.manipulatedUser.username = '';
            this.manipulatedUser.name = '';
            this.manipulatedUser.surname = '';
            this.manipulatedUser.dateOfBirth = '';
            this.manipulatedUser.gender = 'PREFER NOT TO SAY';
            this.manipulatedUser.role = 'MANAGER';
            this.manipulatedUser.status = 'normal';
            this.manipulatedUser.cameFrom = 'restaurant';

            this.visible = "addEditUser";

            $("#username").prop("disabled", false);
        },
        /* Filter parts */
        sortRestName(restaurants) {
            let result = restaurants.filter(rest => rest.name.toLowerCase().includes(this.restaurantFilterObj.name.toLowerCase()));
            
            result.sort(function(a,b){
                return ('' + a.name).localeCompare(b.name);
            });

            if (this.restaurantFilterObj.ascDes.toLowerCase().includes('descending')){
                result.reverse();
            }

            return result;
        },
        sortRestType(restaurants){
            let result = restaurants.filter(rest => rest.type.toLowerCase().includes(this.restaurantFilterObj.type.toLowerCase()));

            result.sort(function(a,b){
                return ('' + a.type).localeCompare(b.type);
            });

            if (this.restaurantFilterObj.ascDes.toLowerCase().includes('descending')){
                result.reverse();
            }

            return result;
        },
        sortRestLoc(restaurants){
            let result = restaurants.filter(rest => rest.location.toLowerCase().includes(this.restaurantFilterObj.location.toLowerCase()));

            result.sort(function(a,b){
                return ('' + a.location).localeCompare(b.location);
            });

            if (this.restaurantFilterObj.ascDes.toLowerCase().includes('descending')){
                result.reverse();
            }

            return result;
        },
        sortRestMark(restaurants){
            let refNumber = parseFloat(this.restaurantFilterObj.mark);

            let result = restaurants.filter(rest => parseFloat(rest.rating) > refNumber);

            if (this.restaurantFilterObj.ascDes.toLowerCase().includes('descending')){
                result.reverse();
            }

            return result;
        },

        sortUserName(users, crit, ascDes){
            let result = users.filter(user => user.name.toLowerCase().includes(crit.toLowerCase()));

            result.sort(function(a,b){
                return ('' + a.name).localeCompare(b.name);
            });

            if (ascDes.toLowerCase().includes('descending')){
                result.reverse();
            }

            return result;
        },
        sortUserSurname(users, crit, ascDes){
            let result = users.filter(user => user.surname.toLowerCase().includes(crit.toLowerCase()));

            result.sort(function(a,b){
                return ('' + a.surname).localeCompare(b.surname);
            });

            if (ascDes.toLowerCase().includes('descending')){
                result.reverse();
            }

            return result;
        },
        sortUserUsername(users,crit, ascDes){
            let result = users.filter(user => user.username.toLowerCase().includes(crit.toLowerCase()));

            result.sort(function(a,b){
                return ('' + a.username).localeCompare(b.username);
            });

            if (ascDes.toLowerCase().includes('descending')){
                result.reverse();
            }

            return result;
        },
        sortShopperPoints(users){
            let ref = parseFloat(this.shopperFilterObj.points);
            let result = users.filter(user => user.collectedPoints > ref);

            result.sort(function(a,b){
                return a.collectedPoints > b.collectedPoints;
            });

            if (this.shopperFilterObj.ascDes.toLowerCase().includes('descending')){
                result.reverse();
            }

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

            this.admins = result;
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
        }
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