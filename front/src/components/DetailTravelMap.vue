<template>
    <div id="map">
        
    </div>
    
</template>
<script>
import store from '../store/index'
import {mapGetters} from 'vuex'

export default {
    name : "DetailTravelMap",
    data () {
        return {
            mapContainer :  null,
            mapOption : null,
            map :  null,
            customOverlay : null,
            nowMarker : null,
         
        }
    },
    mounted() {
        if (window.kakao && window.kakao.maps) {
            setTimeout(() =>{
                this.initMap();
            }, 1000);
            
        } else {
            
            setTimeout(() => {
                const script = document.createElement("script");
            
                script.onload = () => kakao.maps.load(this.initMap);
                script.src =
                    "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=9361ff07bc1d810e5c855e8be3e33c42";
                 document.head.appendChild(script);
            }, 1000);
            
        }
    },
    methods: {
        initMap(){
            this.mapContainer = document.getElementById('map'); // 지도를 표시할 div
            this.mapOption = {
                center: new kakao.maps.LatLng(this.travelInfo.pinList[0].location.y, this.travelInfo.pinList[0].location.x), // 지도의 중심좌표
                level: 4, // 지도의 확대 레벨
            };
            // 마커 이미지의 이미지 주소
            var imageSrc =
                "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
            var imageSize = new kakao.maps.Size(24, 35);
            // 마커 이미지를 생성
            var normalImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

            this.map = new kakao.maps.Map(this.mapContainer, this.mapOption);
            
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

                // var imageList = ``;

                // for (let j = 0; j < this.detailTravelInfo.pinList[index].images.length; j++) {
                //   imageList += 
                //   `<div 
                //     style="background-image: url('data:image/jpeg;base64,`+ this.detailTravelInfo.pinList[index].images[j].base64Image+`');
                //     background-position:center; background-size: cover;">
                //   </div>`
                // }
                

                // var imageCarousel = 
                // `<div class="thumbnail" >
                //   <div class="carousel-container">
                //       <div class="carousel-slide">
                //       `+imageList+`
                //       </div>
                //       <button id="prevBtn">Prev</button>
                //       <button id="nextBtn">Next</button>
                //   </div>
                // </div>`;

                //  var moreInfo = 
                // `<div class="store-card-item-map">
                //     `+imageCarousel+`
                //     <div class="info">
                //       <div class="left-side">
                //           <div class="first-line">
                //           <div class="title">
                //               `+ this.detailTravelInfo.pinList[index].location.place_name +`
                //           </div>
                //           <div class="subtitle">
                //               `+ this.detailTravelInfo.pinList[index].location.category_group_name  +`
                //           </div>          
                //           </div>
                //           <div class="address">
                //               `+ this.detailTravelInfo.pinList[index].location.address_name  +`
                //           </div>
                //       </div>
                //     </div>
                // </div>`;



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

                // console.log(moreInfo2);
               
                

                


                // ###########################

                // var thumbnail = document.createElement('div')
                // thumbnail.classList.add('thumbnail')

                // var carousel_container = document.createElement('div')
                // carousel_container.classList.add('carousel-container')
                
                // var carousel_slide = document.createElement('div')
                // carousel_slide.classList.add('carousel-slide')

                // var prevBtn = document.createElement('button')
                // prevBtn.id = 'prevBtn'
                // prevBtn.appendChild(document.createTextNode('Prev'))
                
                // var nextBtn = document.createElement('button')
                // nextBtn.id = 'nextBtn'
                // nextBtn.appendChild(document.createTextNode('Next'))
                
                // // var imageList = document.createElement('div');
                
                // for (let j = 0; j < this.detailTravelInfo.pinList[index].images.length+2; j++) {
                  
                //   var image = document.createElement('div');

                //   if(j ==0 ){
                //     // let img = `'data:image/png;base64,${this.detailTravelInfo.pinList[index].images[this.detailTravelInfo.pinList[index].images.length-1].base64Image}'`
                //     image.style.backgroundImage = `url(data:image/png;base64,${this.detailTravelInfo.pinList[index].images[this.detailTravelInfo.pinList[index].images.length-1].base64Image})`
                //     image.id = 'lastClone'
                //     // imageVar = `"url('data:image/jpeg;base64,`+this.detailTravelInfo.pinList[index].images[this.detailTravelInfo.pinList[index].images.length-1].base64Image +`');"`
                //     // imageVar = `"url('data:image/jpeg;base64,${this.detailTravelInfo.pinList[index].images[this.detailTravelInfo.pinList[index].images.length-1].base64Image}');"`
                //   }else if(j == this.detailTravelInfo.pinList[index].images.length+1 ){
                //     // let img = `'data:image/png;base64,${this.detailTravelInfo.pinList[index].images[0].base64Image }'`
                //     image.style.backgroundImage = `url(data:image/png;base64,${this.detailTravelInfo.pinList[index].images[0].base64Image })`
                //     image.id = 'firstClone'
                //     // console.log(`url('data:image/png;base64,${this.detailTravelInfo.pinList[index].images[0].base64Image  }')`)
                //     // console.log(image)
                //     // imageVar = `"url('data:image/jpeg;base64,`+this.detailTravelInfo.pinList[index].images[0].base64Image +`');"`
                //     // imageVar = `"url('data:image/jpeg;base64,${this.detailTravelInfo.pinList[index].images[0].base64Image}');"`
                //   }else{
                //     // let img = `data:image/png;base64,${this.detailTravelInfo.pinList[index].images[j-1].base64Image }`
                //     image.style.backgroundImage = `url(data:image/png;base64,${this.detailTravelInfo.pinList[index].images[j-1].base64Image })`
                    
                //     // imageVar =  `"url('data:image/jpeg;base64,`+this.detailTravelInfo.pinList[index].images[j-1].base64Image +`');"`
                //     // imageVar = `"url('data:image/jpeg;base64, ${this.detailTravelInfo.pinList[index].images[j].base64Image}');"`
                //   } 
                //   // imageList.appendChild(image)
                //   // console.log(this.detailTravelInfo.pinList[index].images[j].base64Image)
                  
                //   image.style.backgroundSize = "cover";
                //   carousel_slide.appendChild(image)
                // }
                

                // carousel_container.appendChild(carousel_slide)
                // carousel_container.appendChild(prevBtn)
                // carousel_container.appendChild(nextBtn)
                // thumbnail.appendChild(carousel_container)
                // // console.log(thumbnail)

                // var store_card_item_map = document.createElement('div')
                // store_card_item_map.classList.add('store-card-item-map')

                // var info = document.createElement('div')
                // info.classList.add('info')

                // var left_side = document.createElement('div')
                // left_side.classList.add('left-side')

                // var first_line = document.createElement('div')
                // first_line.classList.add('first-line')

                // var title = document.createElement('div')
                // title.classList.add('title')
                // title.appendChild(document.createTextNode(this.detailTravelInfo.pinList[index].location.place_name))

                // var subtitle = document.createElement('div')
                // subtitle.classList.add('subtitle')
                // subtitle.appendChild(document.createTextNode(this.detailTravelInfo.pinList[index].location.category_group_name))

                // var address = document.createElement('div')
                // address.classList.add('address')
                // address.appendChild(document.createTextNode(this.detailTravelInfo.pinList[index].location.address_name))

                
                // first_line.appendChild(title)
                // first_line.appendChild(subtitle)
                // left_side.appendChild(first_line)
                // left_side.appendChild(address)

                // info.appendChild(left_side)
                // store_card_item_map.appendChild(thumbnail)
                // store_card_item_map.appendChild(info)

                // var carouselSlide = store_card_item_map.querySelector('.carousel-slide')
                // var carouselImages = store_card_item_map.querySelectorAll('.carousel-slide div')
                // var nextButton = store_card_item_map.querySelector('#nextBtn');
                // var prevButton = store_card_item_map.querySelector('#prevBtn');
                

                // let counter = 1;
                // let size = 496;
                // carouselSlide.style.transform = 'translateX(' + (-size * counter) + 'px)';
              
                // // Buttons
                // nextButton.addEventListener('click', ()=> {
                //   if(counter >= carouselImages.length -1) return;
                //   carouselSlide.style.transition = "transform 0.4s ease-in-out";
                //   counter++;
                //   carouselSlide.style.transform = 'translateX(' + (-size * counter) + 'px)';
                // });

                // prevButton.addEventListener('click', ()=> {
                //   if(counter <= 0) return;
                //   carousel_slide.style.transition = "transform 0.4s ease-in-out";
                //   counter--;
                //   carousel_slide.style.transform = 'translateX(' + (-size * counter) + 'px)';
                // });

                // console.log(carouselSlide)
                // console.log(carouselImages[0])
                // console.log(store_card_item_map)


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
        
    },
    computed : {
      ...mapGetters([
        'travelInfo',
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