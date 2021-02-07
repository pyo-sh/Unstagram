package co.kr.datapia.domain;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class FileHandler {
    public List<BoardPicture> parseFileInfo(
            Integer boardID,
            MultipartHttpServletRequest multipartHttpServletRequest
    ) throws Exception{
        // 파일이 빈 것이 들어오면 null 반환
        if(ObjectUtils.isEmpty(multipartHttpServletRequest)){
            return null;
        }

        // 반환을 할 파일 리스트
        List<BoardPicture> fileList = new ArrayList<>();

        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        // 경로를 지정하고 그곳에다가 저장할 심산이다
        String path = "images/" + current_date;
        File file = new File(path);
        // 저장할 위치의 디렉토리가 존지하지 않을 경우
        if(!file.exists()){
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }

        // 파일들을 이제 만져볼 것이다
        // 파일들의 이름들을 받아서 이미지들을 저장할 것
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
        while(iterator.hasNext()){
            // 같은 이름의 파일을 가지고 있을 수 있기 때문에 files 로 받고 반복문 돌린다
            List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());

            for (MultipartFile multipartFile : list){
                // 파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다
                if(!multipartFile.isEmpty()){
                    // jpeg, png, gif 파일들만 받아서 처리할 예정
                    String contentType = multipartFile.getContentType();
                    String originalFileExtension;
                        // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
                    if (ObjectUtils.isEmpty(contentType)){
                        break;
                    }
                    else{
                        if(contentType.contains("image/jpeg")){
                            originalFileExtension = ".jpg";
                        }
                        else if(contentType.contains("image/png")){
                            originalFileExtension = ".png";
                        }
                        else if(contentType.contains("image/gif")){
                            originalFileExtension = ".gif";
                        }
                        // 다른 파일 명이면 아무 일 하지 않는다
                        else{
                            break;
                        }
                    }
                    // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
                    String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
                    // 생성 후 리스트에 추가
                    BoardPicture boardPicture = BoardPicture.builder()
                            .board_idx(boardID)
                            .original_file_name(multipartFile.getOriginalFilename())
                            .stored_file_path(path + "/" + new_file_name)
                            .file_size(multipartFile.getSize())
                            .build();
                    fileList.add(boardPicture);

                    // TODO : 이건 왜 있는지 모르겠따...
                    file = new File(path + "/" + new_file_name);
                    multipartFile.transferTo(file);
                }
            }
        }

        return fileList;
    }
}
