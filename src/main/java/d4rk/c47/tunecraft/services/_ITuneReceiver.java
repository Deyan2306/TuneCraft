package d4rk.c47.tunecraft.services;

public interface _ITuneReceiver {



}

// App (URL) -> [TuneReceiver] -> .mp3 file -> [TuneFormatter | TuneConfigHolder] -> format drive

// TODO: Make it so it automatically determines the source

// Sources: # Further sources are to be added
// YouTube
// Spotify
// SoundCloud

// Each receiver service implements the _ITuneReceiver:
// Example: _YT_TuneReceiver, _Spotify_TuneReceiver, _SC_TuneReceiver
