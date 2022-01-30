package az.fuzuli.gulmammadli.codingtask.services;

import az.fuzuli.gulmammadli.codingtask.dtos.AppDataDto;
import az.fuzuli.gulmammadli.codingtask.entities.AppData;
import az.fuzuli.gulmammadli.codingtask.enums.FileHeader;
import az.fuzuli.gulmammadli.codingtask.repositories.AppDataRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppDataService {

    private final AppDataRepository appDataRepository;
    private final ModelMapper modelMapper;

    public AppDataDto getAppData(String id) {
        return modelMapper.map(appDataRepository.findById(id).orElse(new AppData()), AppDataDto.class);
    }

    //TIMESTAMP FORMAT IS yyyy-MM-ddTHH-mm-ss
    public List<AppDataDto> saveNewAppData(List<String> lines) {

        if(lines.size() > 0) {
            var allowedHeaders = Arrays.stream(FileHeader.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());

            var headers = Arrays.asList(lines.get(0).split(","));
            if (headers.size() == allowedHeaders.size() && allowedHeaders.containsAll(headers)) {
                var headerOrder = new HashMap<FileHeader, Integer>();
                for (var allowedHeader : allowedHeaders) {
                    headerOrder.put(FileHeader.valueOf(allowedHeader), headers.indexOf(allowedHeader));
                }

                var dataList = new ArrayList<AppData>();
                for (int i = 1; i < lines.size(); i++) {
                    var columns = lines.get(i).split(",");
                    if (columns.length == allowedHeaders.size()) {
                        final var primaryKey = columns[headerOrder.get(FileHeader.PRIMARY_KEY)];
                        final var name = columns[headerOrder.get(FileHeader.NAME)];
                        LocalDateTime timeStamp = null;
                        try {
                            timeStamp = LocalDateTime.parse(
                                    columns[headerOrder.get(FileHeader.UPDATED_TIMESTAMP)],
                                    DateTimeFormatter.ISO_LOCAL_DATE_TIME
                            );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        //PRIMARY_KEY, NAME and TIMESTAMP can not be null or empty but DESCRIPTION can be
                        if (primaryKey != null &&
                                !primaryKey.equals("") &&
                                name != null &&
                                !name.equals("") &&
                                timeStamp != null) {

                            dataList.add(
                                    new AppData(
                                            primaryKey,
                                            name,
                                            columns[headerOrder.get(FileHeader.DESCRIPTION)],
                                            timeStamp
                                    )
                            );
                        }
                    }
                }

                return appDataRepository
                        .saveAll(dataList)
                        .stream()
                        .map(e -> modelMapper.map(e, AppDataDto.class))
                        .collect(Collectors.toList());
            }
        }

        return new ArrayList<>();
    }
}
