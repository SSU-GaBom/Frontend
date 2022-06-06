<template>
  <div id="map"></div>
</template>
<script>
export default {
  name: "SimpleMap",
  data() {
    return {
      mapContainer: null,
      mapOption: null,
      map: null,
      customOverlay: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=212c3ad52c21a8156fe970aa01882e45";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      this.mapContainer = document.getElementById("map"); // 지도를 표시할 div
      this.mapOption = {
        center: new kakao.maps.LatLng(35.766826, 127.9786567), // 지도의 중심좌표
        level: 13, // 지도의 확대 레벨
      };

      this.map = new kakao.maps.Map(this.mapContainer, this.mapOption);
      // this.customOverlay = new kakao.maps.CustomOverlay({});
      // this.infowindow = new kakao.maps.InfoWindow({ removable: true });
    },
  },
};
</script>
<style scoped>
#map {
  position: relative;
  width: 70%;
  height: 900px;
}
</style>
