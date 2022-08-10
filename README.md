This app will access github api and display on the screen infos about the repository, the owner, and much more.

### Specifics

` Room for local database `

` Kotlin `

` Koin for Dependency Injection `

` Modularization `

` MVVM `

` Retrofit `


### Overview
![Captura de tela de 2022-07-21 14-03-26](https://user-images.githubusercontent.com/65040111/184015575-234e100c-07ac-4a34-baa1-3717f789ce67.png)
![Captura de tela de 2022-07-21 14-04-11](https://user-images.githubusercontent.com/65040111/184015611-a8f829ed-637e-4a52-96fc-59eb519daee3.png)
![Captura de tela de 2022-07-21 14-05-11](https://user-images.githubusercontent.com/65040111/184015626-9871a9da-5c74-4b0a-a6ab-b2a848f64f1e.png)
![Captura de tela de 2022-07-21 14-15-46](https://user-images.githubusercontent.com/65040111/184015591-39620229-e706-4398-ba25-d99941484875.png)
![Captura de tela de 2022-07-21 14-08-50](https://user-images.githubusercontent.com/65040111/184015520-e02db3e7-99f6-45c8-bf5a-65b095dbc289.png)
![Captura de tela de 2022-07-21 14-15-00](https://user-images.githubusercontent.com/65040111/184015596-473e053c-85b0-4f9a-8814-efa4175039bc.png)

### Dependencies

#### Koin
  ` def koin_version = '3.2.0' `
  
  ` implementation "io.insert-koin:koin-android:$koin_version" `

#### Room
  ` def room_version = "2.4.1" `
  
  ` implementation "androidx.room:room-runtime:$room_version" `
  
  ` kapt "androidx.room:room-compiler:$room_version" `
  
  ` implementation "androidx.room:room-ktx:$room_version" `

 #### Retrofit
   ` implementation 'com.google.code.gson:gson:2.8.9' `
   
   ` implementation 'com.squareup.retrofit2:retrofit:2.9.0' `
   
   ` implementation 'com.squareup.retrofit2:converter-gson:2.9.0' `
