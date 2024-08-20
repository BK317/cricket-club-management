import React, { useState, useEffect } from 'react';
import { Container, Card, Row, Col } from 'react-bootstrap';
import { getMatchSchedules, getNews } from '../services/newsService';
import axios from 'axios';
import './Homepage.css';

function HomePage() {
  const [matchSchedules, setMatchSchedules] = useState([]);
  const [latestNews, setLatestNews] = useState([]);
  const [videos, setVideos] = useState([]);

  useEffect(() => {
    const fetchSchedules = async () => {
      try {
        const data = await getMatchSchedules();
        setMatchSchedules(data || []);
      } catch (error) {
        console.error('Failed to fetch match schedules:', error);
      }
    };

    const fetchNews = async () => {
      try {
        const data = await getNews();
        setLatestNews(data.newsList || []);
      } catch (error) {
        console.error('Failed to fetch news:', error);
      }
    };

    const fetchVideos = async () => {
      const options = {
        method: 'GET',
        url: 'https://cricket-api18.p.rapidapi.com/team/videos',
        params: { teamId: '335974' },
        headers: {
          'x-rapidapi-key': 'b078b0c19dmsh581e59d4154a292p1cb72bjsn027ab6ee2e7f',
          'x-rapidapi-host': 'cricket-api18.p.rapidapi.com'
        }
      };

      try {
        const response = await axios.request(options);
        setVideos(response.data || []);
      } catch (error) {
        console.error('Failed to fetch videos:', error);
      }
    };

    fetchSchedules();
    fetchNews();
    fetchVideos();
  }, []);

  return (
    <div className="d-flex flex-column min-vh-100">
      <Container className="mb-4 flex-grow-1">
        <h1 className="text-center mb-4">Welcome to Cricket Club Management System</h1>

        <Row>
          {/* Latest Matches */}
          <Col md={4}>
            <Card className="mb-4">
              <Card.Header>Latest Matches</Card.Header>
              <Card.Body>
                {matchSchedules.length > 0 ? (
                  matchSchedules.slice(0, 2).map((match, index) => (
                    <Card key={index} className="mb-3">
                      <Card.Body>
                        <Card.Title>{match.title}</Card.Title>
                        <Card.Text>
                          {match.slug} - {new Date(match.startDate).toLocaleDateString()}
                        </Card.Text>
                        <Card.Text>
                          Status: {match.statusText}
                        </Card.Text>
                      </Card.Body>
                    </Card>
                  ))
                ) : (
                  <p>No matches available</p>
                )}
              </Card.Body>
            </Card>
          </Col>

          {/* Latest News */}
          <Col md={4}>
            <Card className="mb-4">
              <Card.Header>Latest News</Card.Header>
              <Card.Body>
                {latestNews.length > 0 ? (
                  latestNews.slice(0, 3).map((item, index) => (
                    item.story ? (
                      <Card key={index} className="mb-3">
                        <Card.Body>
                          <Card.Title>{item.story.hline}</Card.Title>
                          <Card.Text>{item.story.intro}</Card.Text>
                        </Card.Body>
                      </Card>
                    ) : null
                  ))
                ) : (
                  <p>No news available</p>
                )}
              </Card.Body>
            </Card>
          </Col>
          
          {/* Upcoming Tournaments */}
          <Col md={4}>
            <Card className="mb-4">
              <Card.Header>Upcoming Tournaments</Card.Header>
              <Card.Body>
                <Card.Title>Summer Cricket League 2023</Card.Title>
                <Card.Text>
                  Registration for the Summer Cricket League 2023 is now open. Don't miss out!
                </Card.Text>
              </Card.Body>
            </Card>
          </Col>
        </Row>
        
        <Row>
          {/* Videos Section */}
          <Col md={12}>
            <Card className="mb-4">
              <Card.Header>Latest Videos</Card.Header>
              <Card.Body>
                <Row>
                  {videos.length > 0 ? (
                    videos.slice(0, 3).map((video, index) => (
                      <Col md={4} key={index}>
                        <Card className="mb-3">
                          <Card.Img variant="top" src={video.imageUrl} className="video-thumbnail" />
                          <Card.Body>
                            <Card.Title>{video.title}</Card.Title>
                            <Card.Text>{video.summary}</Card.Text>
                          </Card.Body>
                        </Card>
                      </Col>
                    ))
                  ) : (
                    <p>No videos available</p>
                  )}
                </Row>
              </Card.Body>
            </Card>
          </Col>
        </Row>

        <Row>
          {/* Club Announcements */}
          <Col md={12}>
            <Card className="mb-4">
              <Card.Header>Club Announcements</Card.Header>
              <Card.Body>
                <Card.Title>Annual General Meeting</Card.Title>
                <Card.Text>
                  The Annual General Meeting will be held on July 1, 2023. All members are requested to attend.
                </Card.Text>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default HomePage;
