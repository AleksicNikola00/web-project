var app = new Vue({
	el: '#dashboard',
	data: {
        selectedButton : '',
        activeSubmenu : '',
        filterRestaurant : {
            name : '',
            location : '',
            type : '',
            rating : 'all',
            isOpen : false,
            isAsc : true,
            isDesc : false
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
                open : 'true'
            },
            {
                name : 'Neki',
                type : 'Turkish',
                rating : '3',
                address : 'Test b',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad Test b',
                open : 'false'
            },
            {
                name : 'Naki',
                type : 'Turkish',
                rating : '3',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad  Bulevar oslobodjenja',
                open : 'false'
            },
            {
                name : 'Opet',
                type : 'Cheenese',
                rating : '2',
                address : 'Bulevar oslobodjenja 2',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 2',
                open : 'true'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '5',
                address : 'Bulevar oslobodjenja 3',
                city : 'Novi Sad',
                img : '../Images/gold-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 3',
                open : 'true'
            },
            {
                name : 'Ciao',
                type : 'Itelian',
                rating : '1',
                address : 'Bulevar oslobodjenja 5',
                city : 'Novi Sad',
                img : '../Images/silver-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 5',
                open : 'false'
            },
            {
                name : 'Ciao',
                type : 'Cheenese',
                rating : '4',
                address : 'Bulevar oslobodjenja 22',
                city : 'Novi Sad',
                img : '../Images/silver-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 22',
                open : 'true'
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
                open : 'true'
            },
            {
                name : 'Neki',
                type : 'Turkish',
                rating : '3',
                address : 'Test b',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad Test b',
                open : 'false'
            },
            {
                name : 'Naki',
                type : 'Turkish',
                rating : '3',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad  Bulevar oslobodjenja',
                open : 'false'
            },
            {
                name : 'Opet',
                type : 'Cheenese',
                rating : '2',
                address : 'Bulevar oslobodjenja 2',
                city : 'Novi Sad',
                img : '../Images/bronze-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 2',
                open : 'true'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '5',
                address : 'Bulevar oslobodjenja 3',
                city : 'Novi Sad',
                img : '../Images/gold-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 3',
                open : 'true'
            },
            {
                name : 'Ciao',
                type : 'Itelian',
                rating : '1',
                address : 'Bulevar oslobodjenja 5',
                city : 'Novi Sad',
                img : '../Images/silver-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 5',
                open : 'false'
            },
            {
                name : 'Ciao',
                type : 'Cheenese',
                rating : '4',
                address : 'Bulevar oslobodjenja 22',
                city : 'Novi Sad',
                img : '../Images/silver-member.png',
                location : 'Novi Sad Bulevar oslobodjenja 22',
                open : 'true'
            }
        ],
        
	},

	mounted() {
		this.selectedButton = 'restaurants';
        this.changeVisibility();
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

        changeVisibility: function(){
            this.activeSubmenu = this.selectedButton;
        },

        detailsClicked: function(){
            alert(this.filterRestaurant.name);
        },

        updateTextInput: function(){
            document.getElementById('textInput').value=document.getElementById('mark').value; 
        },

        filterOut: function(){
           
        },

        filterByName: function(){
            this.restaurants = this.allRestaurants.filter(r => r.name.toLowerCase().includes(this.filterRestaurant.name.toLowerCase()));
        },

        filterByLocation: function(){
            this.restaurants = this.allRestaurants.filter(r => r.location.toLowerCase().includes(this.filterRestaurant.location.toLowerCase()));
        },

        filterByType: function(){
            this.restaurants = this.allRestaurants.filter(r => r.type.toLowerCase().includes(this.filterRestaurant.type.toLowerCase()));
        },



	},

    computed:{

    },

    watch:{
        'filterRestaurant.name': function(){
            this.filterByName();
        },
        'filterRestaurant.location': function(){
            this.filterByLocation();
        },
        'filterRestaurant.type': function(){
            this.filterByType();
        },

    }

});