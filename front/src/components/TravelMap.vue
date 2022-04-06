<template>
    <div id="map"></div>
</template>

<script>
import geojson from '../assets/all.json'

export default {
  name: "HomeMap",
  data () {
      return {
        mapContainer :  null,
        mapOption : null,
        map :  null,
        customOverlay : null,
        infowindow : null,
        polygons : [],
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
        
        let data = geojson.features;
        let coordinates = [];
        let name = '';

        this.mapContainer = document.getElementById('map'); // 지도를 표시할 div
        this.mapOption = {
          center: new kakao.maps.LatLng(35.766826, 127.9786567), // 지도의 중심좌표
          level: 13, // 지도의 확대 레벨
        };
    
        this.map = new kakao.maps.Map(this.mapContainer, this.mapOption);
        this.customOverlay = new kakao.maps.CustomOverlay({});
        this.infowindow = new kakao.maps.InfoWindow({ removable: true });

        data.forEach((val) => {
            coordinates = val.geometry.coordinates;
            name = val.properties.loc_nm;
           
            if(val.geometry.type == "MultiPolygon"){          
                this.displayArea(coordinates , name , true);
            }else{
                this.displayArea(coordinates , name , false);
            }
            
        });
        console.log("end-1")
      
    },makePolygon(coordinates){
        let path = [];
        let points = [];

        coordinates[0].forEach((coordinate)=>{
            let point = {
                x : coordinate[1],
                y : coordinate[0]
            }
            points.push(point);
            
            path.push(new kakao.maps.LatLng(coordinate[1], coordinate[0]));
        });

        let polygon = new kakao.maps.Polygon({
            map: this.map, //다각형을 표시할 지도 객체
            path: path, // 그려질 다각형의 좌표 배열입니다
            strokeWeight: 2, // 선의 두께입니다
            strokeColor: '#004c80', // 선의 색깔입니다
            strokeOpacity: 0.8, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid', // 선의 스타일입니다
            fillColor: '#fff', // 채우기 색깔입니다
            fillOpacity: 0.7, // 채우기 불투명도 입니다
        });

        return {polygon : polygon , points : points}
    },makeMultiPolygon(coordinates){
        let path = [];
        let points = [];
        
        for (var c in coordinates) {
            var multiCoordinates = coordinates[c];
            let path2 = [];
            for (var z in multiCoordinates[0]) {
                let point = {
                    x : multiCoordinates[0][z][1],
                    y : multiCoordinates[0][z][0]
                }
                
                points.push(point);
                path2.push(new kakao.maps.LatLng(multiCoordinates[0][z][1], multiCoordinates[0][z][0]))
            }
            path.push(path2);
        }
        let polygon =new kakao.maps.Polygon({
            map: this.map, //다각형을 표시할 지도 객체
            path: path, // 그려질 다각형의 좌표 배열입니다
            strokeWeight: 2, // 선의 두께입니다
            strokeColor: '#004c80', // 선의 색깔입니다
            strokeOpacity: 0.8, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid', // 선의 스타일입니다
            fillColor: '#fff', // 채우기 색깔입니다
            fillOpacity: 0.7, // 채우기 불투명도 입니다
        })

        return {polygon : polygon , points : points}
    }, displayArea(coordinates, name , multi){
        
        let points =[];
        let polygon;
        
        
        if(multi){
            console.log(name + 'Multi');
            let returnValue = this.makeMultiPolygon(coordinates);
            
            polygon = returnValue.polygon;
            points.push(returnValue.points)
        }else{
            console.log(name + 'Single');
            let returnValue = this.makePolygon(coordinates);
            
            polygon = returnValue.polygon;
            points.push(returnValue.points)
        }

    
        this.polygons.push(polygon);
        polygon.setMap(this.map);
        
        
        // 다각형에 mouseover 이벤트를 등록하고 이벤트가 발생하면 폴리곤의 채움색을 변경합니다
        // 지역명을 표시하는 커스텀오버레이를 지도위에 표시합니다
        kakao.maps.event.addListener(polygon, 'mouseover', (mouseEvent) => {
            console.log("mouseOver")
            polygon.setOptions({ fillColor: '#09f' });

            this.customOverlay.setContent('<div class="area">' + name + '</div>');
            this.customOverlay.setPosition(mouseEvent.latLng);
            this.customOverlay.setMap(this.map);
        });

        
        // 다각형에 mousemove 이벤트를 등록하고 이벤트가 발생하면 커스텀 오버레이의 위치를 변경합니다
        kakao.maps.event.addListener(polygon, 'mousemove', (mouseEvent)=> {
            console.log("mouseMove")
            this.customOverlay.setPosition(mouseEvent.latLng);
        });
        

        // 다각형에 mouseout 이벤트를 등록하고 이벤트가 발생하면 폴리곤의 채움색을 원래색으로 변경합니다
        // 커스텀 오버레이를 지도에서 제거합니다
        kakao.maps.event.addListener(polygon, 'mouseout', () => {
            console.log("mouseOut")
            polygon.setOptions({ fillColor: '#fff' });
            this.customOverlay.setMap(null);
        });

        // 다각형에 click 이벤트를 등록하고 이벤트가 발생하면 다각형의 이름과 면적을 인포윈도우에 표시합니다.
        kakao.maps.event.addListener(polygon, 'click', (mouseEvent) => {
            
            console.log("click")
            //만약 도클릭시 -> 지도확대되면서 시/군으로 폴리곤 구분
            
            let level = this.map.getLevel()-2;
            this.map.setLevel(level,{anchor : this.centroid(points[0]), animate : {
                duration : 350
            }})
            
            //this.deletePolygon(polygon);
            
            let content ='<div style="padding:2px;">' +name + '</div>';
            this.infowindow.setContent(content);
            this.infowindow.setPosition(mouseEvent.latLng);
            this.infowindow.setMap(this.map);
            
            
            

        });
    },centroid(points){
        let i,j,len,p1,p2,f,area,x,y;
        area = x = y = 0;
        
        for (i = 0 , len = points.length,j= len-1 ; i < len; j=i++) {
            p1 = points[i];
            p2 = points[j];
            
            
            f = p1.y * p2.x - p2.y * p1.x;
            x += (p1.x + p2.x ) * f;
            y += (p1.y + p2.y) * f;
            area += f*3;
            
        }
        console.log(x/area);
        console.log(y/area);
        return new kakao.maps.LatLng(x/area , y/area);
    },
    deletePolygon(polygon){
        /*
        this.polygons.forEach(polygon => { 
            polygon.setMap(null);});
            */
        polygon.setMap(null);
    },zoomMap(){ }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 70%;
  height: 800px;
  display: inline-block;
}

</style>