package org.example.binancetradebotsimulator.kama;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KAMAService {
    private final KAMARepository kamaRepository;

    // kama üstünde kapattıysa fiyat al
    // kama altında kapattıysa sat
    // alış bi önceki mumun kapanışından olucak
    // ve eğer bir önceki mum kapatmışsa al dönücek aynı şekilde satta da bi önceki mum kapattıysa sat dönecek
    public KAMATradeResponseDTO kamaTradeSimulation(KAMATradeRequest request){

return null;
    }
}
