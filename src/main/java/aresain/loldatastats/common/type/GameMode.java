package aresain.loldatastats.common.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameMode {
    CLASSIC,
    ARAM,
    URF,
    TUTORIAL,
    UNKNOWN
}
