package co.kr.datapia.domain;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FileHandler {
    // BoardPicture 객체를 받아서 byte[]를 반환한다.
    public byte[] parseByteFile(BoardPicture boardPicture) throws IOException {
        if(boardPicture == null || boardPicture.getStoredFilePath() == null){
            return null;
        }

        String absolutePath = System.getProperty("user.dir") + "\\";
        InputStream imageStream = new FileInputStream(absolutePath + boardPicture.getStoredFilePath());
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = imageStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        imageStream.close();

        return buffer.toByteArray();
    }

    // MultipartFile 을 받아서 BoardPicture 객체로 변경한다
    public List<BoardPicture> parseFileInfo(
            Board board,
            List<MultipartFile> multipartFiles
    ) throws Exception{
        // 반환을 할 파일 리스트
        List<BoardPicture> fileList = new ArrayList<>();

        // 파일이 빈 것이 들어오면 빈 것을 반환
        if(multipartFiles.isEmpty()){
            return fileList;
        }

        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)
        String absolutePath = System.getProperty("user.dir") + "\\";

        // 경로를 지정하고 그곳에다가 저장할 심산이다
        String path = "images/" + current_date;
        File file = new File(path);

        // 저장할 위치의 디렉토리가 존지하지 않을 경우
        if(!file.exists()){
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }

        // 파일들을 이제 만져볼 것이다
        for (MultipartFile multipartFile : multipartFiles){
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
                String new_file_name = System.nanoTime() + originalFileExtension;
                // 생성 후 리스트에 추가
                BoardPicture boardPicture = BoardPicture.builder()
                        .board(board)
                        .originalFileName(multipartFile.getOriginalFilename())
                        .storedFilePath(path + "/" + new_file_name)
                        .fileSize(multipartFile.getSize())
                        .build();
                fileList.add(boardPicture);

                // 저장된 파일로 변경하여 이를 보여주기 위함
                file = new File(absolutePath + path + "/" + new_file_name);
                multipartFile.transferTo(file);
            }
        }

        return fileList;
    }

//     multipartHttpServletRequest 를 이용한 예제
//    private List<BoardPicture> parseFileInfoExample(
//            Integer boardID,
//            MultipartHttpServletRequest multipartHttpServletRequest
//    ) throws Exception{
//        // 파일이 빈 것이 들어오면 null 반환
//        if(ObjectUtils.isEmpty(multipartHttpServletRequest)){
//            return null;
//        }
//
//        // 반환을 할 파일 리스트
//        List<BoardPicture> fileList = new ArrayList<>();
//
//        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        String current_date = simpleDateFormat.format(new Date());
//
//        // 경로를 지정하고 그곳에다가 저장할 심산이다
//        String path = "images/" + current_date;
//        File file = new File(path);
//        // 저장할 위치의 디렉토리가 존지하지 않을 경우
//        if(!file.exists()){
//            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
//            file.mkdirs();
//        }
//
//        // 파일들을 이제 만져볼 것이다
//        // 파일들의 이름들을 받아서 이미지들을 저장할 것
//        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
//        while(iterator.hasNext()){
//            // 같은 이름의 파일을 가지고 있을 수 있기 때문에 files 로 받고 반복문 돌린다
//            List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
//
//            for (MultipartFile multipartFile : list){
//                // 파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다
//                if(!multipartFile.isEmpty()){
//                    // jpeg, png, gif 파일들만 받아서 처리할 예정
//                    String contentType = multipartFile.getContentType();
//                    String originalFileExtension;
//                    // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
//                    if (ObjectUtils.isEmpty(contentType)){
//                        break;
//                    }
//                    else{
//                        if(contentType.contains("image/jpeg")){
//                            originalFileExtension = ".jpg";
//                        }
//                        else if(contentType.contains("image/png")){
//                            originalFileExtension = ".png";
//                        }
//                        else if(contentType.contains("image/gif")){
//                            originalFileExtension = ".gif";
//                        }
//                        // 다른 파일 명이면 아무 일 하지 않는다
//                        else{
//                            break;
//                        }
//                    }
//                    // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
//                    String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
//                    // 생성 후 리스트에 추가
//                    BoardPicture boardPicture = BoardPicture.builder()
//                            .boardIdx(boardID)
//                            .originalFileName(multipartFile.getOriginalFilename())
//                            .storedFilePath(path + "/" + new_file_name)
//                            .fileSize(multipartFile.getSize())
//                            .build();
//                    fileList.add(boardPicture);
//
//                    // 저장된 파일로 변경한다
//                    file = new File(path + "/" + new_file_name);
//                    multipartFile.transferTo(file);
//                }
//            }
//        }
//
//        return fileList;
//    }
}
