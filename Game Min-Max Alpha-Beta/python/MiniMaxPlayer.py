import math
from Board import Board
from Player import Player
from copy import deepcopy


class MiniMaxPlayer(Player):
    MAX_DEPTH = 2
    INFINITY = 9999

    def bfs(self, opponent: Player):
        for player in [self, opponent]:
            destination = (
                self.board.get_white_goal_pieces()
                if player.color == "white"
                else self.board.get_black_goal_pieces()
            )
            visited = {}
            distances = {}
            for row in self.board.map:
                for piece in row:
                    visited[piece] = False
                    distances[piece] = self.INFINITY

            player_piece = self.board.get_piece(*player.get_position())

            queue = []
            queue.append(player_piece)
            visited[player_piece] = True
            distances[player_piece] = 0

            while queue:
                piece = queue.pop(0)

                for i in self.board.get_piece_neighbors(piece):
                    if visited[i] == False:
                        distances[i] = distances[piece] + 1
                        visited[i] = True
                        queue.append(i)

            min_distance = self.INFINITY
            for piece, dist in distances.items():
                if piece in destination:
                    if dist < min_distance:
                        min_distance = dist

            if player == self:
                self_distance = min_distance
            else:
                opponent_distance = min_distance

        return self_distance, opponent_distance

    def evaluate(self, opponent):

        self_distance, opponent_distance = self.bfs(opponent)

        if self_distance > opponent_distance + 2 :
            total_score = (opponent_distance) - self_distance


        else:
            total_score = (opponent_distance / 3) - self_distance

        return total_score



    def get_best_action(self, opponent):
        if self.Dictionary.get(self.board) != None :
             return self.Dictionary[self.board]
        value , action = self.max_value(depth=0 , alpha =-self.INFINITY ,beta = self.INFINITY , min_Player=opponent)

        self.Dictionary[self.convertor()] = action
        return action

    def max_value(self , depth  , alpha , beta ,  min_Player):
        if (depth >= 2):
            return self.evaluate(min_Player) , None
        bestVal = -self.INFINITY
        action1 =None

        for action in self.get_legal_actions(min_Player):

                self.play(action)
                value, action2 = self.min_value(depth=depth + 1,alpha= alpha,beta= beta, min_Player=min_Player)

                if (value > bestVal ):
                    bestVal = value
                    action1 = action

                    alpha = max(alpha, bestVal)
                if (bestVal > beta):
                    self.undo_last_action()

                    return bestVal, action1

                self.undo_last_action()

        return bestVal , action1

    def min_value(self, depth, alpha, beta,  min_Player):

        if (depth >= 2):
             return self.evaluate(min_Player), None
        bestVal = self.INFINITY
        action1 = None

        for action in min_Player.get_legal_actions(self):

            self.play(action,is_evaluating=True)
            value, action2 = self.max_value(depth+1 , alpha , beta , min_Player)
            if (value < bestVal ):
                bestVal = value
                action1 = action
                beta = min(alpha, bestVal)
            if (bestVal < alpha):

                self.undo_last_action()
                return bestVal, action1

            self.undo_last_action()
        return bestVal, action1
    def convertor(self ):
        mapString = ""
        for x in range(9) :
            for y in range(9):
                piece = self.get_piece1(x, y)

                if piece.state == "empty":
                   mapString += "empty"
                elif piece.state == "white":
                    mapString += "white"
                else:
                    mapString += "Black"

                if piece.r_side == "block":
                  mapString += "block"
        return mapString

    def get_piece1(self, x, y):
            x = min(x,8)
            y = min(y, 8)
            return self.board.map[y][x]

                # best_action_value = -self.INFINITY
        # best_action = None
        # for action in self.get_legal_actions(opponent):
        #     self.play(action, is_evaluating=True)
        #     if self.is_winner():
        #         self.undo_last_action()
        #         return action
        #
        #     action_value = self.evaluate(opponent)
        #     if action_value > best_action_value:
        #         best_action_value = action_value
        #         best_action = action
        #
        #     self.undo_last_action()
        #
        # return best_action

