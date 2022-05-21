<template>
    <div>
        <v-dialog 
            width="700px"
            v-model="dialog"
        >
        <template v-slot:activator="{ on , attrs}">
            <v-btn
            v-bind="attrs"
            v-on="on"
            icon
            darks
            >
            <v-icon>mdi-image-multiple</v-icon>
            </v-btn>
        </template>

        <!-- <v-sheet
            color="white"
            elevation="1"
        > -->
        <v-card
            class="mx-auto"
            max-width="900"
        >
            <v-carousel hide-delimiters>
                <v-carousel-item
                    v-for="(image,i) in pin.images"
                    :key="i"
                    v-bind:src="'data:image/jpeg;base64,'+ image.base64Image"
                ></v-carousel-item>
            </v-carousel>

            <v-card-title>
                <v-spacer>{{pin.location.place_name}}</v-spacer>
            </v-card-title>
            
            <v-card-subtitle>
                {{pin.location.category_name}}<br>
                {{pin.location.address_name}}
            </v-card-subtitle>
            

            <v-card-actions>
            <v-btn
                color="black lighten-2"
                text
            >
                더보기
            </v-btn>

            <v-spacer></v-spacer>

            <v-btn
                icon
                @click="show = !show"
            >
                <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
            </v-btn>
            <v-btn icon @click="dialog=false">
                <v-icon>mdi-close-circle</v-icon>
            </v-btn>  
            </v-card-actions>

            <v-expand-transition>
            <div v-show="show">
                <v-divider></v-divider>

                <v-card-text>
                    {{pin.locationContent}}
                </v-card-text>
            </div>
            </v-expand-transition>
        </v-card>
        <!-- </v-sheet> -->
        


        <!-- <v-sheet
            color="white"
            elevation="1"
        >
        <v-carousel hide-delimiters>
            <v-carousel-item
                v-for="(image,i) in pin.images"
                :key="i"
                v-bind:src="'data:image/jpeg;base64,'+ image.base64Image"
            ></v-carousel-item>
        </v-carousel>

        <v-text>{{pin.location.place_name}}</v-text>
        <v-text>{{pin.locationContent}}</v-text>
        <v-btn @click="dialog=false">끄기</v-btn>           
        </v-sheet> -->

    </v-dialog>
  </div>
</template>
<script>


export default {
    name : 'CardViewComp',
    props : {
        pin : null
    },
    data() {
        return {
            dialog : false,
            show: false,
            
        }
    },
    
}
</script>
<style scoped>
    
</style>