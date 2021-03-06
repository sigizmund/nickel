package com.sigizmund.nickel;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.repackaged.com.google.common.base.Function;
import com.google.appengine.repackaged.com.google.common.collect.ImmutableList;
import com.google.appengine.repackaged.com.google.common.collect.Iterables;

import javax.inject.Named;

import nickel.Chapter;
import nickel.FictionConverter$;

@Api(name = "nickelApi", version = "v1", namespace = @ApiNamespace(
    ownerDomain = "sigizmund.com",
    ownerName = "sgzmd",
    packagePath = ""
))
public class NickelAPI {
  @ApiMethod(name = "nickel.startDownload", path = "startDownload", httpMethod = ApiMethod.HttpMethod.POST)
  public StartBookDownloadResponse startBookDownload(
      StartBookDownloadRequest request) {

    StartBookDownloadResponse resp = new StartBookDownloadResponse(
        "",
        "",
        "",
        ImmutableList.copyOf(Iterables.transform(
        FictionConverter$.MODULE$.getStoryChapters(request.getChapterUrl(), request.getChapterText()),
        new Function<Chapter, String>() {
          @Override
          public String apply(Chapter chapter) {
            return chapter.url();
          }
        }
    )));

    return resp;
  }

  @ApiMethod(name = "nickel.addChapter", path = "addChapter", httpMethod = ApiMethod.HttpMethod.POST)
  public void addChapter(StartBookDownloadRequest request) {
    return;
  }
}
