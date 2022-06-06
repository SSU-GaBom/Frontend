<template>
    <div id="map">
        
    </div>
    
</template>
<script>
import store from '../store/index'
import {mapGetters} from 'vuex'
import {getTravelDetail,} from "../api/travel";


export default {
    name : "DetailTravelMap",
    data () {
        return {
            mapContainer :  null,
            mapOption : null,
            map :  null,
            customOverlay : null,
            nowMarker : null,
            travelInfo : []
        }
    },
    mounted() {
        
        if (window.kakao && window.kakao.maps) {
          this.initMap();
        } else {
            const script = document.createElement("script");
            script.onload = () => kakao.maps.load(this.initMap);
            script.src ="//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=212c3ad52c21a8156fe970aa01882e45";
            document.head.appendChild(script);
        }
    },
    watch :{
      '$route' () {
        this.initMap();
      }
    },
    methods: {
        initMap(){
            this.fetchTravelInfo();
            console.log("DetailTravelMap.initMap")
            
            this.mapContainer = document.getElementById('map'); // 지도를 표시할 div
            this.mapOption = {
                center: new kakao.maps.LatLng(35.766826, 127.9786567), // 지도의 중심좌표
                level: 13, // 지도의 확대 레벨
            };

            this.map = new kakao.maps.Map(this.mapContainer, this.mapOption);
            this.addClusterer();
        },
        addClusterer(){
          setTimeout( () => {
            console.log("DetailTravelMap.addClusterer")
            this.mapOption = {
                center: new kakao.maps.LatLng(this.travelInfo.pinList[0].location.y, this.travelInfo.pinList[0].location.x), // 지도의 중심좌표
                level: 4, // 지도의 확대 레벨
            };

            this.map = new kakao.maps.Map(this.mapContainer, this.mapOption);
            // 마커 이미지의 이미지 주소
            var imageSrc =
                "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
            var imageSize = new kakao.maps.Size(24, 35);
            // 마커 이미지를 생성
            var normalImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

            let map = this.map;
            
            for (let index = 0; index < this.travelInfo.pinList.length; index++) {
                
                var x = this.travelInfo.pinList[index].location.x
                var y = this.travelInfo.pinList[index].location.y
                
                // 마커가 표시될 위치입니다 
                var markerPosition  = new kakao.maps.LatLng(y, x); 

                // 마커를 생성합니다
                var marker = new kakao.maps.Marker({
                    map : map,
                    position: markerPosition,
                    title: this.travelInfo.pinList[index].location.place_name, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                    image: normalImage, // 마커 이미지
                });


                var basicInfo = `<div class="customoverlay">
                    <a href="`+this.travelInfo.pinList[index].location.place_url+`" 
                    target="_blank">
                    <span class="title">` 
                        + this.travelInfo.pinList[index].location.place_name +
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




                var moreInfo2 = `<div class="store-card-item-map">
                    <div
                      class="thumbnail"
                      style="background-image: url('data:image/jpeg;base64,`+ this.travelInfo.pinList[index].images[0].base64Image+`');
                                background-position:center;">
                    </div>
                    <div class="info">
                      <div class="left-side">
                          <div class="first-line">
                            <div class="title">
                                `+ this.travelInfo.pinList[index].location.place_name +`
                            </div>
                            <div class="subtitle">
                                `+ this.travelInfo.pinList[index].location.category_group_name  +`
                            </div>          
                          </div>
                          <div class="address">
                              `+ this.travelInfo.pinList[index].location.address_name  +`
                          </div>
                      </div>
                    </div>
                  </div>`;

                var customOverlayMore = new kakao.maps.CustomOverlay({
                  clickable: true,
                  content: moreInfo2,
                  position: markerPosition,
                  xAnchor: 0.5,
                  yAnchor: 1.1,
                  zIndex: 3,
                });

            
                kakao.maps.event.addListener(map, 'click', this.resetMarker())
                kakao.maps.event.addListener(marker,'mouseover',this.makeOverListner(map,marker,customOverlay))
                kakao.maps.event.addListener(marker,'mouseout',this.makeOutListner(customOverlay))
                kakao.maps.event.addListener(marker,'click',this.makeClickListner(map,marker,customOverlayMore))

                // 마커가 지도 위에 표시되도록 설정합니다
                marker.setMap(map);
            }
          } , 4000)
        },
        makeOverListner(map, marker, customOverlay){
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
          }
          
        },
        makeOutListner(customOverlay){
          return () => {

            // customOverlay.setVisible(false);
            customOverlay.setMap(null);
          }

        },
        makeClickListner(map,marker,customOverlayMore){
          return () => {
            if (this.nowMarker) {
              
              this.nowMarker.overlay.setMap(null)
              // this.selectedMarker.overlay.setMap(null); // 기존 ovelay 제거
              
            }
            const tempMarker = {
              name : marker.Gb,
              overlay : customOverlayMore
            }
            this.nowMarker = tempMarker
            customOverlayMore.setMap(map); // 지도에 올림
            
            map.panTo(marker.getPosition());
            }
        },
        
        resetMarker(){
          return () => {
            if(this.nowMarker){
              this.nowMarker.overlay.setMap(null); // 기존 ovelay 제거
              if (this.nowMarker) {
                this.nowMarker = null;
              }
            }
          }
        },
        async fetchTravelInfo() {
          console.log("DetailTravelMap.fetchTravelInfo()");
          const response = await getTravelDetail(
            this.$route.params.travelContentId
          );
          this.travelInfo = response.data;

        },
        
    },
    computed : {
      ...mapGetters([
        // 'travelInfo',
        // 'selectedMarker'
      ])
    },
}
</script>
<style lang="scss">

#map {
    position: relative;
    width: 70%;
    height: 780px;
}

.customoverlay {
  position: relative;
  bottom: 0px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
  a {
    display: block;
    text-decoration: none;
    color: #000;
    text-align: center;
    border-radius: 6px;
    font-size: 14px;
    font-weight: bold;
    overflow: hidden;
    background: #3859B4;
    /* background: $primary url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center; */
  }
  .title {
    display: block;
    text-align: center;
    background: #fff;
    margin-right: 35px;
    padding: 10px 15px;
    font-size: 14px;
    font-weight: bold;
  }
}
.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.store-card-item-map {
  position: relative;
  bottom: 0px;
  width: 500px;
  height: 350px;
  margin: 10px;
  line-height: 1.2;
  border: 2px solid #EAEAEE;
  .thumbnail {
    
    width: 496px;
    /* height: $width * 9 / 16; */
    height: 496px * 9 * 0.0625;
    border-radius: 4px 4px 0 0;
    // background-image: url('data:image/jpeg;base64,');
    background-color: #888;
    background-size: cover;
    overflow: hidden;
    position: relative;

    .carousel-container{
      width: 496px;
      margin: 30px auto;
      border: 0px solid #000;
      overflow: hidden;
      position: relative;
    }
    .carousel-slide{
      display: flex;
      width: 100%;
      height: 493px * 9 * 0.0625;
    }
    #prevBtn{
      position:absolute;
      top: 50%;
      left: 0;
      transform: translate(0%, -50%);
      width: 20px;
      height: 36px;
      background: url('@/assets/logo.png') no-repeat;
      text-indent: -9999px;
    }
    #nextBtn{
      position:absolute;
      top: 50%;
      right: 0;
      transform: translate(0%, -50%);
      width: 20px;
      height: 36px;
      background: url('@/assets/logo.png') no-repeat;
      text-indent: -9999px;
    }
  }
  .info {
    display: flex;
    height: 70px;
    justify-content: space-between;
    align-items: center;
    background-color: rgba(255, 255, 255, 0.955);
    border-radius: 0 0 4px 4px;
    .left-side {
      margin-left: 170px;
      .first-line {
        /* display: flex;
        align-items: flex-end; */
        .title {
          font-size: 12px;
          color: #000;
          margin-right: 10px;
        }
        .subtitle {
          font-size: 11px;
          color: #000;
        }
      }
      .address {
        font-size: 11px;
        color: #000;
      }
    }
    
  }
}

</style>