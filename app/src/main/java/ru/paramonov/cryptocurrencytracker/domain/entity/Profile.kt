package ru.paramonov.cryptocurrencytracker.domain.entity

data class Profile(
    val id: Int = 1,
    val fullName: String = "Никита Парамонов",
    val screenName: String = "Никита",
    val profilePhoto: String = "https://sun9-48.userapi.com/impg/rIHyvTNGLzWEOwpCHeRaeQWS5UKzlhv4cxBQzg/HkvI2wG8AqI.jpg?size=809x1080&quality=95&sign=6170933317f02e616e8f32a0acb542e9&type=album",
    val homeTown: String = "Москва",
    val bDate: String = "10.11.2002"
)