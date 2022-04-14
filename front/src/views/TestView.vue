<template>
  <div>
    <!-- 지도를 표시할 div 입니다 -->
    <div id="map" style="width:80%;height:700px;"></div>
  </div>

</template>

<script>



export default {
    name: 'TestView',
    data () {
        return {
            
        }
    },
    mounted() {
      if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=9361ff07bc1d810e5c855e8be3e33c42";
      document.head.appendChild(script);
      }
    },
    
    methods: {
        initMap() {
            const container = document.getElementById("map");
            const options = {
                center: new kakao.maps.LatLng(33.450701, 126.570667),
                level: 5,
            };

            //지도 객체를 등록합니다.
            //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
            this.map = new kakao.maps.Map(container, options);
        },
    },
    
    components: {
    },
  }
</script>