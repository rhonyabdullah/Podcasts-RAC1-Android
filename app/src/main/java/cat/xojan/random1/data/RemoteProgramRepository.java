package cat.xojan.random1.data;

import java.io.IOException;
import java.util.List;

import cat.xojan.random1.domain.entities.Podcast;
import cat.xojan.random1.domain.entities.PodcastData;
import cat.xojan.random1.domain.entities.Program;
import cat.xojan.random1.domain.entities.ProgramData;
import cat.xojan.random1.domain.repository.ProgramRepository;
import rx.Observable;

public class RemoteProgramRepository implements ProgramRepository {

    private final Rac1RetrofitService mService;

    public RemoteProgramRepository(Rac1RetrofitService service) {
        mService = service;
    }

    @Override
    public Observable<List<Program>> getProgramListObservable() throws IOException {
        return mService.getProgramData().map(ProgramData::getPrograms);
    }

    @Override
    public Observable<List<Podcast>> getPodcastByProgram(String programId) throws IOException {
        return mService.getPodcastData(programId).map(PodcastData::getPodcasts);
    }

    @Override
    public Observable<List<Podcast>> getPodcastBySection(String programId, String sectionId)
            throws IOException {
        return mService.getPodcastData(programId, sectionId).map(PodcastData::getPodcasts);
    }
}