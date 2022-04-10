<template>
  <div id="left-bar">
    <div class='left'>
       <menu-list></menu-list>
    </div>
    <img id='left_btn' @click='toggle' src='../assets/logo.png'>
  </div>
</template>

<script>
import MenuList from './MenuList.vue'

export default {
  name: 'LeftBar',
  components :{
    MenuList
  },
  data () {
    return {
      
      toggle: this.enter
    }
  },
  methods: {
    enter: function (el, done) {
      this.toggle = this.leave
      // eslint-disable-next-line no-undef
      var left = (400 - (parseInt($('#left_btn').css('width')) / 2)) + 'px'
      // eslint-disable-next-line no-undef
      $('.left').stop().animate({left: '0px'}, 800)
      // eslint-disable-next-line no-undef
      $('#left_btn').stop().animate({left: left}, 800)
      // eslint-disable-next-line no-undef
      $('#left_btn').css({transform: 'rotate(180deg)'})
    },
    leave: function (el, done) {
      this.toggle = this.enter
      // eslint-disable-next-line no-undef
      var left = (0 - (parseInt($('#left_btn').css('width')) / 2)) + 'px'
      // eslint-disable-next-line no-undef
      $('.left').stop().animate({left: '-400px'}, 800)
      // eslint-disable-next-line no-undef
      $('#left_btn').stop().animate({left: left}, 800)
      // eslint-disable-next-line no-undef
      $('#left_btn').css({transform: 'rotate(0deg)'})
    },
    resize: function () {
      // eslint-disable-next-line no-undef
      $(window).resize(function () {
        var height = (window.innerHeight / 2) + 'px'
        // eslint-disable-next-line no-undef
        $('#left_btn').css({'top': height})
      })
    }
  },
    created () {
      this.resize()
    }
  
}
</script>

<style scoped>
#left-bar {
  position:absolute;
  left:0;
  z-index:10;
}
  a{color:black}
  a:hover{color:rgb(98, 255, 111);}
  .left{    
    position:fixed; 
    top:0px; 
    left:-400px; 
    width:400px; 
    height:1000px; 
    background:rgb(98, 255, 111); 
    padding:40px 0
  }
  .left ul {padding: 0 30px}
  .left ul li { font-size:25px;  height:75px;} 
  .left ul li#logo {font-family: 'Passion One', cursive; font-size:50px; height:170px;}
  .left ul li#logo div {line-height: 0.8}
  .left ul li#company {font-size:16px; margin-top:150px; }
  #left_btn {position:fixed; top:495px; left:-60px; cursor:pointer}
</style>