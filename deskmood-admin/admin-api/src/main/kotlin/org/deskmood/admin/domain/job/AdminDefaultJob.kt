package org.deskmood.admin.domain.job

enum class AdminDefaultJob(
    private val value: String,
    private val order: Int
) {
    PROGRAMMING("개발", 1),
    MANAGEMENT("경영/비즈니스", 2),
    MARKETING("마케팅/광고", 3),
    DESIGN("디자인", 4),
    BUSINESS("영업", 5),
    RETAIL("고객서비스/리테일", 6),
    MEDIA("미디어", 7),
    ENGINEERING("엔지니어링/설계", 8),
    HR("HR", 9),
    GAME_PROGRAMMING("게임제작", 10),
    FINANCE("금융", 11),
    MANUFACTURING("제조/생산", 12),
    EDUCATION("교육", 13),
    MEDICAL("의료/제약/바이오", 14),
    TRADE("물류/무역", 15),
    LAW("법률/집행기관", 16),
    FOOD("식/음료", 17),
    ARCHITECT("건설/시설", 18),
    WELFARE("공공/복지", 19),
    ETC("기타", 20);

    fun toAdminJob(): AdminJob {
        return AdminJob(
            value = value,
            order = order
        )
    }
}
