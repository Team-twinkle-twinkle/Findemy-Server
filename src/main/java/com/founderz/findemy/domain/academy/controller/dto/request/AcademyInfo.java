package com.founderz.findemy.domain.academy.controller.dto.request;

import com.founderz.findemy.domain.academy.entity.enums.SiDo;
import com.founderz.findemy.domain.academy.entity.enums.SiGunGu;

public record AcademyInfo(
        String academy_name,
        String address,
        String tel_number,
        String academy_img_url,
        String introduction,
        SiDo si_do,
        SiGunGu si_gun_gu
) {
}
