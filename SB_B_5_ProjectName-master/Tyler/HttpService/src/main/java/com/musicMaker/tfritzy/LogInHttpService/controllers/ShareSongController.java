package com.musicMaker.tfritzy.LogInHttpService.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.musicMaker.tfritzy.LogInHttpService.DatabaseConnectionManager;
import com.musicMaker.tfritzy.LogInHttpService.Queries;
import com.musicMaker.tfritzy.LogInHttpService.SongQueries;
import com.musicMaker.tfritzy.LogInHttpService.DAO.RequestResult;
import com.musicMaker.tfritzy.LogInHttpService.DAO.Song;

@Controller
public class ShareSongController {

	@Autowired
	private DatabaseConnectionManager connection;

	@RequestMapping(value = "/shareSong", method = RequestMethod.POST)
	public @ResponseBody RequestResult addNewWorker(@RequestBody Song song) throws SQLException {
		
		RequestResult result = new RequestResult();
		
		if (SongQueries.doesSongExist(song.getSongName(), song.getComposer(), connection)) {
			String query = "update song set shared = true where name = '" + song.getSongName() + "'";
			connection.updateStatement(query);
			result = new RequestResult("None. The song was shared.", true);
			return result;
		}
		return new RequestResult("The song to be shared does not exist.", false);
	}
}
