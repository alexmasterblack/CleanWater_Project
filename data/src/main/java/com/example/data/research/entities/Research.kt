package com.example.data.research.entities

data class Research(
    // айди, номер
    val id: Long,
    // коллекционный номер, полевой
    val collectionNumber: Long,
    // дата исследования с временем
    val date: String,
    // широта вручную
    val latitudeByHand: String,
    // широта с телефона
    val latitudeAuto: String,
    // долгота вручную
    val longitudeByHand: String,
    // долгота с телефона
    val longitudeAuto: String,
    // регион
    val region: String,
    // район
    val district: String,
    // ближайший населенный пункт
    val settlement: String,
    // название водного объекта
    val nameReservoir: String,
    // бассейн
    val riverBasin: String,
    // длина водотока (в км) протяженность - одно и тоже?
    val lengthStream: Int,
    // температура воздуха
    val airTemperature: Int,
    // температура воды
    val waterTemperature: Int,
    // комментарий
    val comment: String,
    // часть речного бассейна
    val partRiverBasin: String,
    // продольная зона водотока
    val longitudinalZone: String,
    // продольный элемент русла
    val longitudinalElementRiverbed: String,
    // поперечный элемент русла
    val transverseElementRiverbed: String,
    // скорость течения
    val currentVelosity: Int,
    // ширина русла
    val widthRiverbed: Int,
    // средняя глубина
    val averageDepth: Int,
    // глубина в месте отбора пробы ?
    val depthSamplingPoint: Int,
    // тип донного субстрата
    val bottomSubstrateType: String,
    // развитие водной растительности
    val developmentAquaticVegetation: String,
    // тип прибрежной растительности
    val typeRiparianVegetation: String,
    // освещенность русла
    val illuminationRiverbed: String,
    // антропогенное влияние
    val anthropogenicImpact: Int,
    // метод отбора проб
    val samplingMethod: String,
    // тип пробоотборника
    val typeSampler: String
)