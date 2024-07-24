<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Personal Java Web Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div id="main">
            <div id="header">
                <a class="links" id="name" href="https://github.com/cashhollister2u">[Cash Hollister]</a>
            </div>
            <div id="projects">
                <h2 class="projectHeader">Projects:</h2>
                <div id="projectsMain">
                    <div id="Project1" class="projectInd">
                        <a class="links" id="projectLinks" href="https://github.com/cashhollister2u/Tidbyt-Open-Sourced">[Tidbyt-Open-Sourced]</a>
                            <div class="imageContainer">
                                <img id="matrixSpotify" class="projectImages" src="images/martixSpotify.jpeg">
                                <img id="matrixClock" class="projectImages" src="images/matrixClock.jpeg">
                                <img id="matrixStock" class="projectImages" src="images/matrixStock.jpeg">
                                <img id="matrixClockStock" class="projectImages" src="images/matrixClockStock.jpeg">
                            </div>
                        <h3>Description:</h3>
                        <p class="projectDescription">This project is a DIY LED Matrix dashboard inspired by <a class="textLinks" href="https://tidbyt.com">Tydbit</a>. It utlizes periodic api calls to 
                                                      collect and display data. I gained exprience with minipulating data, object oriented programming, modular code structure, basic hardware configuration, and
                                                      linux os configuration.
                        </p>
                    </div>
                    <div id="Project2" class="projectInd">
                        <a class="links" id="projectLinks" href="https://github.com/cashhollister2u/GooseApp-Tauri">[Goose.com]</a>
                        <a class="links" id="miniProjectLinks" href="https://github.com/cashhollister2u/gooseBackend_deployed">[Goose.com Backend Repository]</a>
                        <a class="links" id="miniProjectLinks" href="https://github.com/cashhollister2u/cashhollister2u/releases/tag/v0.1.0">[Goose.com Download]</a>
                        <div class="imageContainer">
                            <img id="gooseDashboard" src="images/gooseDashboard.png">
                        </div>
                        <h3>Description:</h3>
                        <p class="projectDescription">This was my first real project and first ever web development project (the code is a mess). It is a social media clone focused on sharing stock market prefrences. 
                                                      The project allows the user to create an account, modify an account, send encrypted messages, follow other users, and track the most common stocks mentioned.
                                                      I gained indepth knowledge on frontend/backend architecture. I was able to gain further insight on the deployment 
                                                      of fullstack web applications and serverside configurations. 
                                                      <br>
                                                      <br>
                                                      ***The backend is currently unavailable externally but can be accessed on request.***   
                                                    </p>
                    </div>
                    <div id="Project3" class="projectInd">
                        <a class="links" id="projectLinks" href="https://github.com/cashhollister2u/Spotipy-CircuitPython">[Spotipy-CircuitPython]</a>
                        <h3>Description:</h3>
                        <p class="projectDescription">This is a library I created on neccessity when starting my LED Matrix project. It is a light weight library designed to run with Adafruit Circuit Python.
                                                      It can be utilized on highly constrained devices where full python versions are not supported. The library crafts api calls to authenticate the spotify 
                                                      user and subsequently control the user's spotify account.
                        </p>
                    </div>
                </div>
            </div>
        <div>
            <h2 id="commentsHeader">Comments:  </h2>
            <div class="commentsMain">
                <h3 class="commentSubHeader">- New Comment -</h3>
                <form method="post" action="${pageContext.request.contextPath}/comments">
                    <label for="commentName">Name: </label>
                    <br>
                    <input type="text" name="name" id="commentName" maxlength="35" required>
                    <div id="charLimitName">
                        <p>(limit: 35)</p>
                    </div>
                    <br>
                    <label for="comment" id="newCommentLabel">New comment: </label>
                    <br>
                    <textarea type="text" name="comment" id="comment" class="newComment" maxlength="500" required></textarea>
                    <div id="charLimitTextArea">
                        <p>(limit: 500)</p>
                    </div>
                    <input type="submit" value="submit" id="commentSubmit">
                </form>
                <h3 class="commentSubHeader">- Posted Comments(${numberOfComments}) -</h3>
                <c:forEach var="comment" items="${commentsList}">
                    <div class="postedComments">
                        <p class="postedName">${comment.name}</p>
                        <textarea class="postedComment" readonly>${comment.comment}</textarea>
                    </div>
                </c:forEach>
            </div>
        </div>
        </div>
    </body>
</html>
