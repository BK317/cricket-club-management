package com.app;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.app.dto.MatchDTO;
import com.app.dto.PlayerDTOAdmin;
import com.app.entities.Match;
import com.app.entities.Player;

public class ModelMapperConfig {
    public static void configure(ModelMapper modelMapper) {
        // Player to PlayerDTOAdmin mapping
        modelMapper.addMappings(new PropertyMap<Player, PlayerDTOAdmin>() {
            @Override
            protected void configure() {
                map().setName(source.getUser().getUsername()); // Assuming `User` has a `getUsername()` method
                map().setTeamId(source.getTeamId().getId());
                map().setUserId(source.getUser().getId());
            }
        });

        // Match to MatchDTO mapping
        modelMapper.addMappings(new PropertyMap<Match, MatchDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setDate(source.getDate());
                map().setType(source.getType());
                map().setResult(source.getResult());
                map().setTeamAScore(source.getTeamAScore());
                map().setTeamBScore(source.getTeamBScore());
                map().setGroundId(source.getGround().getId());
                map().setTournamentId(source.getTournament().getId());
                map().setTeamAId(source.getTeamA().getId());
                map().setTeamBId(source.getTeamB().getId());
            }
        });
    }
}
