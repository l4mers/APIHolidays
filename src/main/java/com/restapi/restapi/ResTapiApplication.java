package com.restapi.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ResTapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResTapiApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner hej(LanguageRepository languageRepository,
//                                 AmenityRepository amenityRepository
//                                 ){
//        return (args -> {
//
//            amenityRepository.saveAll(List.of(Amenity.builder()
//                            .amenity("Wifi")
//                            .build(),
//                            Amenity.builder()
//                                    .amenity("Free Parking")
//                                    .build(),
//                            Amenity.builder()
//                                    .amenity("Paid Parking")
//                                    .build(),
//                            Amenity.builder()
//                                    .amenity("Public Transportation")
//                                    .build(),
//                            Amenity.builder()
//                                    .amenity("EV Charging Station")
//                                    .build(),
//                            Amenity.builder()
//                                    .amenity("Handicap Accessibility")
//                                    .build(),Amenity.builder()
//                                    .amenity("Restrooms")
//                                    .build(),Amenity.builder()
//                                    .amenity("Cot / Baby crib")
//                                    .build(),Amenity.builder()
//                                    .amenity("Highchair")
//                                    .build(),Amenity.builder()
//                                    .amenity("Air Conditioning")
//                                    .build(),Amenity.builder()
//                                    .amenity("TV")
//                                    .build(),
//                            Amenity.builder()
//                                    .amenity("Kitchen")
//                                    .build(),Amenity.builder()
//                                    .amenity("BBQ Grill")
//                                    .build(),
//                            Amenity.builder()
//                                    .amenity("Fire Pit")
//                                    .build(),Amenity.builder()
//                                    .amenity("Indoor Fireplace")
//                                    .build(),Amenity.builder()
//                                    .amenity("Outdoor Shower")
//                                    .build(),Amenity.builder()
//                                    .amenity("Washing Machine")
//                                    .build(),Amenity.builder()
//                                    .amenity("Dishwasher")
//                                    .build(),Amenity.builder()
//                                    .amenity("Pool")
//                                    .build(),Amenity.builder()
//                                    .amenity("Hot tub")
//                                    .build(),Amenity.builder()
//                                    .amenity("Sauna")
//                                    .build(),Amenity.builder()
//                                    .amenity("Tennis")
//                                    .build(),Amenity.builder()
//                                    .amenity("Beach Access")
//                                    .build(),Amenity.builder()
//                                    .amenity("Lake Access")
//                                    .build(),Amenity.builder()
//                                    .amenity("Diving Access")
//                                    .build(),Amenity.builder()
//                                    .amenity("Fishing")
//                                    .build(),Amenity.builder()
//                                    .amenity("Riding")
//                                    .build(),Amenity.builder()
//                                    .amenity("Exercise Nearby")
//                                    .build(),Amenity.builder()
//                                    .amenity("Hunting")
//                                    .build(),Amenity.builder()
//                                    .amenity("Golf")
//                                    .build(),Amenity.builder()
//                                    .amenity("Ski")
//                                    .build(),Amenity.builder()
//                                    .amenity("First Aid Kit")
//                                    .build(),Amenity.builder()
//                                    .amenity("Fire Extinguisher")
//                                    .build(),
//                            Amenity.builder()
//                                    .amenity("Smoke Alarm")
//                                    .build()));
//
//
//
//
//
//            List<String> list = List.of("Afrikaans",
//                    "Albanian",
//                    "Amharic",
//                    "Arabic",
//                    "Armenian",
//                    "Assamese",
//                    "Aymara",
//                    "Azerbaijani",
//                    "Bambara",
//                    "Basque",
//                    "Belarusian",
//                    "Bengali",
//                    "Bhojpuri",
//                    "Bosnian",
//                    "Bulgarian",
//                    "Catalan",
//                    "Cebuano",
//                    "Chinese",
//                    "Corsican",
//                    "Croatian",
//                    "Czech",
//                    "Danish",
//                    "Dhivehi",
//                    "Dogri",
//                    "Dutch",
//                    "English",
//                    "Esperanto",
//                    "Estonian",
//                    "Ewe",
//                    "Finnish",
//                    "French",
//                    "Frisian",
//                    "Galician",
//                    "Georgian",
//                    "German",
//                    "Greek",
//                    "Guarani",
//                    "Gujarati",
//                    "Haitian Creole",
//                    "Hausa",
//                    "Hawaiian",
//                    "Hebrew",
//                    "Hindi",
//                    "Hmong",
//                    "Hungarian",
//                    "Icelandic",
//                    "Igbo",
//                    "Ilocano",
//                    "Indonesian",
//                    "Irish",
//                    "Italian",
//                    "Japanese",
//                    "Javanese",
//                    "Kannada",
//                    "Kazakh",
//                    "Khmer",
//                    "Kinyarwanda",
//                    "Konkani",
//                    "Korean",
//                    "Krio",
//                    "Kurdish",
//                    "Kurdish",
//                    "Kyrgyz",
//                    "Lao",
//                    "Latin",
//                    "Latvian",
//                    "Lingala",
//                    "Lithuanian",
//                    "Luganda",
//                    "Luxembourgish",
//                    "Macedonian",
//                    "Maithili",
//                    "Malagasy",
//                    "Malay",
//                    "Malayalam",
//                    "Maltese",
//                    "Maori",
//                    "Marathi",
//                    "Meiteilon",
//                    "Mizo",
//                    "Mongolian",
//                    "Myanmar",
//                    "Nepali",
//                    "Norwegian",
//                    "Nyanja",
//                    "Odia",
//                    "Oromo",
//                    "Pashto",
//                    "Persian",
//                    "Polish",
//                    "Portuguese",
//                    "Punjabi",
//                    "Quechua",
//                    "Romanian",
//                    "Russian",
//                    "Samoan",
//                    "Sanskri",
//                    "Scots Gaelic",
//                    "Sepedi",
//                    "Serbian",
//                    "Sesotho",
//                    "Shona",
//                    "Sindhi",
//                    "Sinhala",
//                    "Slovak",
//                    "Slovenian",
//                    "Somali",
//                    "Spanish",
//                    "Sundanese",
//                    "Swahili",
//                    "Swedish",
//                    "Tagalog",
//                    "Tajik",
//                    "Tamil",
//                    "Tatar",
//                    "Telugu",
//                    "Thai",
//                    "Tigrinya",
//                    "Tsonga",
//                    "Turkish",
//                    "Turkmen",
//                    "Twi",
//                    "Ukrainian",
//                    "Urdu",
//                    "Uyghur",
//                    "Uzbek",
//                    "Vietnamese",
//                    "Welsh",
//                    "Xhosa",
//                    "Yiddish",
//                    "Yoruba",
//                    "Zulu");
//
//            list.forEach(e->{
//                languageRepository.save(new Language(e));
//            });
//        });
//    }
}
