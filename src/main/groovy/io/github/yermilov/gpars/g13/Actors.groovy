package io.github.yermilov.gpars.g13

import static groovyx.gpars.actor.Actors.*

def fetchLatestNews = reactor {
    news.fetchLatest()
}

def extractText = messageHandler {
//    when { Tweet tweet -> reply tweet.status }
//    when { FacebookPost facebookPost -> reply facebookPost.post }
//    when { BlogArticle blogArticle -> reply blogArticle.text }
}

def save = reactor { text ->
    println text
}

def main = actor {
    loop {
        fetchLatestNews.send()
        react { latestNews ->
            extractText latestNews
            react { text ->
                save text
            }
        }
    }
}


