<template>
  <div id="map"></div>
</template>

<script>
import geojson from "../assets/all.json";
import { mapGetters } from "vuex";
import { getMyTravel } from "../api/travel";
import store from "../store/index";

export default {
  name: "HomeMap",
  data() {
    return {
      mapContainer: null,
      mapOption: null,
      map: null,
      customOverlay: null,
      nowMarker: null,
      clusterer: null,
      myTravelList: [],
      // infowindow: null,
      // polygons: [],
    };
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
      console.log("TravelMap.initMap()");
      this.mapContainer = document.getElementById("map"); // 지도를 표시할 div
      this.mapOption = {
        center: new kakao.maps.LatLng(35.766826, 127.9786567), // 지도의 중심좌표
        level: 13, // 지도의 확대 레벨
      };

      this.map = new kakao.maps.Map(this.mapContainer, this.mapOption);
      // this.customOverlay = new kakao.maps.CustomOverlay({});
      // this.infowindow = new kakao.maps.InfoWindow({ removable: true });
      this.addClusterer();
    },
    addClusterer() {
      // console.log("addClusterer")
      //로그인한 상태면
      if (this.myUserId) {
        //여행 데이터를 불러온다!
        if (!this.myTravelList[0]) {
          this.fetchMyTravel(this.myUserId);
        }

        setTimeout(() => {
          // console.log("3초후")
          // 마커 이미지의 이미지 주소
          var imageSrc =
            "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
          var imageSize = new kakao.maps.Size(24, 35);
          // 마커 이미지를 생성
          var normalImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

          // 마커 클러스터러를 생성합니다
          this.clusterer = new kakao.maps.MarkerClusterer({
            map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
            averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
            minLevel: 10, // 클러스터 할 최소 지도 레벨
          });

          // console.log(this.myTravelList.length)
          for (let i = 0; i < this.myTravelList.length; i++) {
            for (let j = 0; j < this.myTravelList[i].pinList.length; j++) {
              var x = this.myTravelList[i].pinList[j].location.x;
              var y = this.myTravelList[i].pinList[j].location.y;

              // 마커가 표시될 위치입니다
              var markerPosition = new kakao.maps.LatLng(y, x);

              // 마커를 생성합니다
              var marker = new kakao.maps.Marker({
                map: this.map,
                position: markerPosition,
                title: this.myTravelList[i].pinList[j].location.place_name, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                image: normalImage, // 마커 이미지
              });

              var basicInfo =
                `<div class="customoverlay">
                <a href="` +
                this.myTravelList[i].pinList[j].location.place_url +
                `" 
                target="_blank">
                <span class="title">` +
                this.myTravelList[i].pinList[j].location.place_name +
                `</span>
                </a>
                </div>`;

              var customOverlay = new kakao.maps.CustomOverlay({
                clickable: true,
                content: basicInfo,
                position: markerPosition,
                xAnchor: 0.5,
                yAnchor: 1.9,
                zIndex: 3,
              });

              var moreInfo =
                `<div class="store-card-item-map">
                <div
                  class="thumbnail"
                  style="background-image: url('data:image/jpeg;base64,` +
                this.myTravelList[i].pinList[j].images[0].base64Image +
                `');
                            background-position:center;">
                </div>
                <div class="info">
                  <div class="left-side">
                      <div class="first-line">
                        <div class="title">
                            ` +
                this.myTravelList[i].pinList[j].location.place_name +
                `
                        </div>
                        <div class="subtitle">
                            ` +
                this.myTravelList[i].pinList[j].location.category_group_name +
                `
                        </div>          
                      </div>
                      <div class="address">
                          ` +
                this.myTravelList[i].pinList[j].location.address_name +
                `
                      </div>
                  </div>
                </div>
              </div>`;

              var customOverlayMore = new kakao.maps.CustomOverlay({
                clickable: true,
                content: moreInfo,
                position: markerPosition,
                xAnchor: 0.5,
                yAnchor: 1.1,
                zIndex: 3,
              });

              kakao.maps.event.addListener(
                this.map,
                "click",
                this.resetMarker()
              );
              kakao.maps.event.addListener(
                marker,
                "mouseover",
                this.makeOverListner(this.map, marker, customOverlay)
              );
              kakao.maps.event.addListener(
                marker,
                "mouseout",
                this.makeOutListner(customOverlay)
              );
              kakao.maps.event.addListener(
                marker,
                "click",
                this.makeClickListner(this.map, marker, customOverlayMore)
              );
              this.clusterer.addMarker(marker);
              // console.log(this.clusterer)
            }
          }
        }, 3000);
      }
    },
    makeOverListner(map, marker, customOverlay) {
      return () => {
        if (this.nowMarker) {
          // 선택되어 있는 마커가 있을 경우

          if (marker.getTitle() !== this.nowMarker.name) {
            // 선택되어 있는 마커와 새로 선택된 마커의 상호명이 다를 경우
            customOverlay.setMap(map); // 지도에 올림
          }
        } else {
          // 선택되어 있는 마커가 없을 경우
          customOverlay.setMap(map); // 지도에 올림
          // customOverlay.setVisible(true); // 지도에서 보이게 함
        }
      };
    },
    makeOutListner(customOverlay) {
      return () => {
        // customOverlay.setVisible(false);
        customOverlay.setMap(null);
      };
    },
    makeClickListner(map, marker, customOverlayMore) {
      return () => {
        if (this.nowMarker) {
          this.nowMarker.overlay.setMap(null);
          // this.selectedMarker.overlay.setMap(null); // 기존 ovelay 제거
        }
        const tempMarker = {
          name: marker.Gb,
          overlay: customOverlayMore,
        };
        this.nowMarker = tempMarker;
        customOverlayMore.setMap(map); // 지도에 올림

        map.panTo(marker.getPosition());
      };
    },
    resetMarker() {
      return () => {
        if (this.nowMarker) {
          this.nowMarker.overlay.setMap(null); // 기존 ovelay 제거
          if (this.nowMarker) {
            this.nowMarker = null;
          }
        }
      };
    },
    async fetchMyTravel(userId) {
      // console.log(userId)
      const response = await getMyTravel(userId);
      // console.log(response.data)
      for (let i = 0; i < response.data.length; i++) {
        // console.log(response.data[i])
        store.commit("SET_TRAVEL", response.data[i]);
      }
    },
  },
  computed: {
    ...mapGetters(["myUserId", "myTravelList"]),
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  position: relative;
  width: 70%;
  height: 900px;
}
</style>
