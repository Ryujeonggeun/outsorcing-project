package com.sparta.outsorcingproject.service;

import com.sparta.outsorcingproject.dto.MenuRequestDto;
import com.sparta.outsorcingproject.dto.MenuResponseDto;
import com.sparta.outsorcingproject.entity.Menu;
import com.sparta.outsorcingproject.entity.Store;
import com.sparta.outsorcingproject.repository.MenuRepository;
import com.sparta.outsorcingproject.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final MessageSource messageSource;

    @Transactional
    public String createMenu(long storeId, MenuRequestDto requestDto) {
        // 나중에 권한 확인해서 오더가 맞는지, 스토어 소유주 맞는지

        Store store = storeRepository.findStoreById(storeId,messageSource);
        //Store store = storeRepository.findById(storeId).orElseThrow(
        //        ()->new IllegalArgumentException(messageSource.getMessage("not.find.store",null, Locale.getDefault())));

        Menu menu = Menu.builder()
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .description(requestDto.getDescription())
                .store(store)
                .build();

        menuRepository.save(menu);

        return "메뉴 저장 성공";
    }

    public MenuResponseDto getMenu(long storeId, long menuId) {
        Store store = storeRepository.findStoreById(storeId,messageSource);
        Menu menu = menuRepository.findMenuById(menuId,messageSource);

        return new MenuResponseDto(menu);
    }

    @Transactional
    public String updateMenu(long storeId, long menuId, MenuRequestDto requestDto) {
        Store store = storeRepository.findStoreById(storeId,messageSource);
        Menu menu = menuRepository.findMenuById(menuId,messageSource);

        menu.update(requestDto);

        return "메뉴 업데이트 완료";
    }

    @Transactional
    public String deleteMenu(long storeId, long menuId) {
        Store store = storeRepository.findStoreById(storeId,messageSource);
        Menu menu = menuRepository.findMenuById(menuId,messageSource);
        String str = menu.getName() + " 메뉴 삭제 완료";
        menuRepository.delete(menu);
        return str;
    }

    public List<MenuResponseDto> getMenus(long storeId) {
        Store store = storeRepository.findStoreById(storeId,messageSource);

        List<Menu> menus = menuRepository.findAllByStoreId(storeId);

        List<MenuResponseDto> menusDto = menus.stream().map(MenuResponseDto::new).collect(Collectors.toList());

        return menusDto;
    }
}
