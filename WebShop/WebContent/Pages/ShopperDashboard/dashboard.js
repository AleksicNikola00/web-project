var webShop = new Vue({
    el: '#dashboard',
    data: {
        restaurants : [
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad'
            },
            {
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad'
            },{
                name : 'Ciao',
                type : 'Italian',
                rating : '4.7*',
                address : 'Bulevar oslobodjenja',
                city : 'Novi Sad'
            }
        ],
        selectedButton : {},
        currentUser : {},
        visible : 'myProfile'
    },
    mounted (){
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