var app = new Vue({
	el: '#dashboard',
	data: {
        selectedButton : '',
        activeSubmenu : '',
        restaurants : [
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

        updateTextInput: function(){
            document.getElementById('textInput').value=document.getElementById('mark').value; 
        }

	}
});