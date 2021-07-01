var app = new Vue({
	el: '#dashboard',
	data: {
        selectedButton : '',
        activeSubmenu : ''
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

        updateTextInput: function(value){
            //alert(value)
            document.getElementById('textInput').value=document.getElementById('mark').value; 
        }

	}
});