var webShop = new Vue({
    el: '#dashboard',
    data: {
        products : [
            {name: 'pera'},
            {name: 'mika'},
            {name: 'zika'},
            {name: 'zare'},
            {name: 'afs'},
            {name: 'fsfs'},
            {name: 'pesfsfra'}
        ],
        selectedButton : {},
        currentUser : {},
        visible : 'myProfile'
    },
    mounted (){
        this.products.push({name: 'lol'});
    },
    methods : {
        selectSubmenu : function(submenu){
            if (Object.keys(this.selectedButton).length != 0){
                this.selectedButton.removeClass('btn-primary');
                this.selectedButton.addClass('btn-light');
            }
            this.selectedButton = $('#' + submenu);
            this.selectedButton.removeClass('btn-light');
            this.selectedButton.addClass('btn-primary');
            this.visible = submenu;
        }
    }
})